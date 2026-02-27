package com.kevinroditi.pokemonapp_kevinroditi.di

/**
 * Provides network related dependencies
 *
 * Responsabilities:
 * - Config. OkhttpClient
 * - Config. Retrofit
 * - Provide API service
 *
 * Architecture:
 * - Installed in SingletonComponent to ensure single instance
 *   accross entire app lifecycle
 *
 *   Why singleton:
 *   - Retrofit and OkHttp are expensive to create
 *   - They should be shared
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/"

    // Provides HTTP logging interceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

    /** Provides OkHttp client
     * Config:
     * - 30s timeouts for reliability
     * - Logging interceptor
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides Retrofit instance
     *
     * Using Moshi for JSON parsing
     * Lightweight and Kotlin friendly
     */

    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    /**
     * Provides API service impl.
     */
    @Provides
    @Singleton
    fun providePokeApiService(
        retrofit: Retrofit
    ): PokeApiService{
        return retrofit.create(PokeApiService::class.java)

}