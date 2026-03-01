<table>
  <tr>
    <td><img src="![Screenshot_20260301_150449_pokemonapp_kevinroditi](https://github.com/user-attachments/assets/df60e8aa-587b-4960-a44d-66553cde1bbf)" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/f1b81dfa-6481-4d12-9f8f-5acd4d32390b" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/8739b486-6a79-47d2-b22f-9915dcc42cc4" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/9ecc5460-dab2-41a1-9521-a9c8f50243d3" width="100%"></td>
    
    
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/353b684b-8383-4c7e-a5be-2126fff1d73b" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/38d048ee-2df9-4788-9827-045aaf97dc07" width="100%"></td>
    <td><img src="https://github.com/user-attachments/assets/dff0851e-21f7-4f20-9018-9a5d40215ba4" width="100%"></td>
  </tr>
</table>

# Pokédex – Enterprise-Grade Android Architecture Documentation
Clean Architecture | Jetpack Compose | Kotlin | Coroutines | Flow | Hilt | Room | Paging3 | Material 3

---

## ⚠️ IMPORTANT – Setup Instructions (English)

This project’s active implementation resides in the `dev` branch.

Before building the application:

1. Clone the repository.
2. Switch to the `dev` branch:3. Open the project in Android Studio (latest stable version recommended).
4. Sync Gradle.
5. Run the application on:
- An Android Emulator (API 26+ recommended), or
- A physical device with USB debugging enabled.

Minimum requirements:
- Android Studio Iguana or newer
- Kotlin 1.9+
- Gradle 8+
- JDK 17


--- 
# Pokemon App – Technical Documentation 
**Branch:** `dev` 
**Author:** Kevin Roditi 
🇪🇸 [Versión en Español](#pokemon-app---documentacion-tecnica) 
---

# 1. Project Overview

Pokémon App is a production-ready Android application built using **Kotlin**, **Jetpack Compose**, **Clean Architecture**, and **MVVM** principles.

The project demonstrates senior-level Android engineering standards with a strong focus on:

- Strict separation of concerns
- Scalable and maintainable architecture
- Biometric authentication
- Advanced search capabilities
- Persistent favorites system
- Offline-first approach
- Comprehensive unit and UI testing
- Future-ready authentication abstraction (Firebase-ready, currently disabled)

The entire implementation resides under the `dev` branch.

---

# 2. Architecture

The project follows **Clean Architecture**, structured into three primary layers:## 2.1 Domain Layer

- Pure Kotlin
- No Android framework dependencies
- Fully testable
- Contains:
  - Domain models
  - Repository interfaces
  - Business UseCases

### Implemented UseCases

- AuthenticateUserUseCase
- CheckSessionUseCase
- LogoutUseCase
- SearchPokemonUseCase
- ToggleFavoriteUseCase
- GetFavoritePokemonUseCase
- ObserveFavoriteStatusUseCase

The Domain layer remains completely independent from infrastructure and UI concerns.

---

## 2.2 Data Layer

Responsible for:

- Remote API communication (Retrofit)
- Local database persistence (Room)
- Encrypted session storage (EncryptedSharedPreferences)
- Repository implementations
- DTO to Domain mapping

### Room Configuration

**Entity**
- FavoritePokemonEntity

**DAO**
- insertFavorite()
- deleteFavoriteById()
- getAllFavorites() → PagingSource
- observeFavoriteById()

Favorites persist across:

- App restarts
- Configuration changes
- Process recreation
- Offline usage

The data layer cleanly merges API results with locally stored favorites.

---

## 2.3 Presentation Layer

Built using:

- Jetpack Compose
- ViewModels
- Hilt Dependency Injection
- Kotlin Flow
- Paging 3

### Screens

- LoginScreen (Biometric Authentication)
- HomeScreen (Search + Paging)
- FavoritesScreen (Favorite Pokémon list)

---

# 3. Biometric Authentication

Authentication is implemented using:

- Android BiometricPrompt
- AES-256 MasterKey
- EncryptedSharedPreferences
- LocalAuthRepository

### Authentication Flow

1. Application launches
2. CheckSessionUseCase verifies encrypted session
3. If session exists → Navigate to Home
4. If not → Show LoginScreen
5. User taps Authenticate
6. BiometricPrompt validates device owner (Fingerprint / Face Recognition)
7. On success:
   - Encrypted session flag stored securely
   - Navigation to Home screen

### Security Design

- No plaintext storage
- No backend dependency
- Fully local authentication
- AuthRepository abstraction prepared for future Firebase integration

---

# 4. Firebase-Ready Architecture (Disabled)

Authentication logic is abstracted via `AuthRepository`.

Active implementation:
- LocalAuthRepository

Scaffolded but disabled:
- FirebaseAuthRepository

To enable Firebase in the future:
- Replace Hilt binding from LocalAuthRepository to FirebaseAuthRepository
- Add Firebase configuration files
- No changes required in Domain or Presentation layers

This ensures scalability without introducing unnecessary runtime complexity.

---

# 5. Advanced Search System

The Home screen supports dynamic search by:

- Name
- ID
- Pokémon Type

### Search Logic

- Numeric input → Search by ID
- Valid Pokémon type → Search by Type
- Otherwise → Search by Name

### Search Bar Hints

English:
"Search by name, ID, or type"

Spanish:
"Buscar por nombre, ID o tipo (ej. fuego, agua, planta)"

Search integrates with Paging 3 and supports filtering and sorting preferences.

---

# 6. Favorites System

Users can:

- Tap a heart icon to add/remove favorites
- See animated state transitions
- Navigate to a dedicated Favorites screen
- Persist favorites locally via Room

### Features

- Optimistic UI updates
- Flow-based observation
- Paging 3 integration
- Offline persistence
- Survives process death and configuration changes

Favorites are merged with remote data to ensure accurate UI representation.

---

# 7. Testing Strategy

Testing stack:

- JUnit 4
- Mockito
- Robolectric
- Turbine
- Espresso
- Hilt Testing
- kotlinx-coroutines-test

## 7.1 Unit Tests (app/src/test)

Structured under:Coverage includes:

- Domain UseCases
- Repository logic
- ViewModel behavior
- Search logic branching
- Favorites merging logic
- Biometric session handling

Target coverage:

- Domain ≥ 90%
- ViewModels ≥ 85%
- Data ≥ 80%

---

## 7.2 UI Tests (app/src/androidTest)

Covers:

- Login flow
- Biometric trigger behavior (mocked)
- Search input behavior
- Favorites toggle behavior
- Paging rendering
- Configuration change persistence

Critical user journeys are fully tested.

---

# 8. How to Run the Project

1. Clone the repository
2. Checkout the `dev` branch
3. Open the project in Android Studio (latest stable)
4. Sync Gradle
5. Run on an emulator or physical device with biometric capability enabled

No Firebase configuration required.

---

# 9. Conclusion

This implementation demonstrates:

- Clean Architecture discipline
- Production-ready biometric authentication
- Scalable authentication abstraction
- Advanced multi-criteria search
- Persistent favorites system
- Strong automated testing coverage
- Enterprise-level Android engineering standards

The project reflects readiness for professional Android development environments and scalable real-world applications.

---

## Pokemon App - Documentacion tecnica 
**Rama:** `dev` 
**Autor:** Kevin Roditi 
🇬🇧 [English Version](#pokemon-app---technical-documentation) ---

# 1. Descripción General

Pokémon App es una aplicación Android lista para producción construida con **Kotlin**, **Jetpack Compose**, **Clean Architecture** y **MVVM**.

El proyecto demuestra estándares de ingeniería Android de nivel senior, con enfoque en:

- Separación estricta de responsabilidades
- Arquitectura escalable y mantenible
- Autenticación biométrica
- Sistema avanzado de búsqueda
- Sistema de favoritos persistente
- Enfoque offline-first
- Testing automatizado completo
- Arquitectura preparada para Firebase (deshabilitada)

Toda la implementación se encuentra en la rama `dev`.

---

# 2. Arquitectura

La aplicación sigue **Clean Architecture**, dividida en:## 2.1 Capa Domain

- Kotlin puro
- Sin dependencias del framework Android
- Totalmente testeable
- Contiene:
  - Modelos de dominio
  - Interfaces de repositorio
  - Casos de uso

Casos de uso implementados:

- AuthenticateUserUseCase
- CheckSessionUseCase
- LogoutUseCase
- SearchPokemonUseCase
- ToggleFavoriteUseCase
- GetFavoritePokemonUseCase
- ObserveFavoriteStatusUseCase

---

## 2.2 Capa Data

Responsable de:

- Comunicación con API (Retrofit)
- Persistencia local con Room
- Almacenamiento de sesión cifrado
- Implementaciones de repositorios
- Mapeo DTO → Domain

Room incluye:

- FavoritePokemonEntity
- DAO para insertar, eliminar y observar favoritos

Los favoritos persisten tras:

- Reinicio de la aplicación
- Cambios de configuración
- Muerte del proceso
- Uso sin conexión

---

## 2.3 Capa Presentation

Construida con:

- Jetpack Compose
- ViewModels
- Hilt
- Kotlin Flow
- Paging 3

Pantallas:

- LoginScreen (Autenticación biométrica)
- HomeScreen (Búsqueda + Paging)
- FavoritesScreen (Lista de favoritos)

---

# 3. Autenticación Biométrica

Implementada con:

- BiometricPrompt
- MasterKey AES-256
- EncryptedSharedPreferences
- LocalAuthRepository

Flujo:

1. La app inicia
2. Se verifica la sesión cifrada
3. Si existe → Navega a Home
4. Si no → Muestra Login
5. Usuario se autentica con biometría
6. Se guarda sesión cifrada
7. Navegación a Home

Diseño de seguridad:

- Sin almacenamiento en texto plano
- Sin dependencia backend
- Arquitectura lista para integrar Firebase en el futuro

---

# 4. Arquitectura Preparada para Firebase

AuthRepository abstrae la autenticación.

Implementación activa:
- LocalAuthRepository

Implementación futura:
- FirebaseAuthRepository (deshabilitada)

Para activar Firebase:
- Cambiar binding en Hilt
- Agregar configuración
- Sin modificar Domain ni Presentation

---

# 5. Sistema Avanzado de Búsqueda

Permite buscar por:

- Nombre
- ID
- Tipo

Lógica:

- Entrada numérica → ID
- Tipo válido → Tipo
- Otro texto → Nombre

Hint en español:
"Buscar por nombre, ID o tipo (ej. fuego, agua, planta)"

---

# 6. Sistema de Favoritos

El usuario puede:

- Marcar/desmarcar favoritos
- Ver animación de corazón
- Acceder a pantalla dedicada de favoritos
- Persistencia local con Room

Sobrevive a:

- Reinicio
- Muerte de proceso
- Rotación de pantalla

---

# 7. Estrategia de Testing

Stack:

- JUnit
- Mockito
- Robolectric
- Turbine
- Espresso
- Hilt Testing

Cobertura objetivo:

- Domain ≥ 90%
- ViewModels ≥ 85%
- Data ≥ 80%

Flujos críticos de usuario completamente cubiertos.

---

# 8. Ejecución

1. Clonar repositorio
2. Cambiar a rama `dev`
3. Abrir en Android Studio
4. Ejecutar en dispositivo con biometría habilitada

No requiere configuración de Firebase.

---

# 9. Conclusión

Este proyecto demuestra:

- Disciplina en Clean Architecture
- Seguridad biométrica lista para producción
- Arquitectura escalable
- Búsqueda avanzada multi-criterio
- Favoritos persistentes
- Testing sólido
- Estándares Android de nivel empresarial

Representa preparación para entornos profesionales de desarrollo Android y aplicaciones reales escalables.

