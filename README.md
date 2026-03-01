<<<<<<< HEAD
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/bb2684c9-1f90-4c4c-b912-7aac0de15663" width="300" alt="Screenshot_20260228_164220"/></td>
    <td><img src="https://github.com/user-attachments/assets/5a24e295-5688-4059-a855-6fd97cf9057f" width="300" alt="Screenshot_20260228_164256"/></td>
    <td><img src="https://github.com/user-attachments/assets/1f741b77-1e8d-472a-ac78-7aa92a26bd0b" width="300" alt="Screenshot_20260228_164702"/></td>
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

🇪🇸 [Ir a la versión en Español](#-instrucciones-importantes--configuración-español)

---

# 🇬🇧 Pokédex – Enterprise-Grade Android Architecture Documentation

---

## 1. Project Overview

This document describes the architectural structure, design decisions, scalability considerations, and production-readiness characteristics of the Pokédex Android application.

The application is implemented using modern Android development standards, emphasizing:

- Clean architectural layering
- Reactive state management
- Clear separation of concerns
- Testability-oriented design
- Scalability readiness
- Long-term maintainability

---

## 2. Architectural Principles

The system is built upon the following principles:

- Dependency inversion
- Separation of responsibilities
- Immutable state modeling
- Reactive data streams
- Single source of truth
- Platform-independent domain logic
- Explicit mapping between layers
- Lifecycle awareness

Architectural decisions prioritize extensibility and maintainability.

---

## 3. High-Level Architecture

The application follows Clean Architecture:

Presentation Layer  
↓  
Domain Layer  
↓  
Data Layer  

Dependencies flow inward. Outer layers depend only on inner abstractions.

---

## 4. Layer Responsibilities

### 4.1 Presentation Layer

Responsibilities:

- Rendering UI using Jetpack Compose
- Observing state from ViewModels
- Emitting user interactions
- Managing navigation

Characteristics:

- Stateless composables
- State hoisting
- Immutable UI state models
- Lifecycle-aware Flow collection
- Strict separation between UI and logic

---

### 4.2 Domain Layer

Responsibilities:

- Business logic orchestration
- Definition of repository contracts
- Use case abstraction
- Pure domain modeling

Characteristics:

- Kotlin-only implementation
- No Android framework dependencies
- Fully testable in isolation
- Explicit use case classes per business operation

---

### 4.3 Data Layer

Responsibilities:

- Remote API integration (Retrofit)
- Local persistence (Room)
- Paging data management (Paging3)
- DTO-to-domain mapping
- Repository implementation

Characteristics:

- Explicit mappers between layers
- Flow-based data emission
- Separation between network, database, and repository logic
- Encapsulation of data source complexity

---

## 5. Data Flow Model

User Interaction  
→ ViewModel  
→ Use Case  
→ Repository  
→ Remote / Local Data Source  
→ Domain Model Mapping  
→ Flow Emission  
→ UI Recomposition  

Unidirectional data flow is enforced.

---

## 6. State Management Strategy

The state management approach includes:

- Immutable UI state data classes
- Single state holder per screen
- Flow-based state exposure
- Structured coroutine scope usage
- Lifecycle-aware state collection

---

## 7. Paging Strategy

Paging3 is used to:

- Prevent full dataset loading into memory
- Enable incremental loading
- Improve scroll performance
- Maintain UI responsiveness

LazyVerticalGrid ensures efficient rendering of paged content.

---

## 8. Persistence Strategy

Favorites are stored locally using Room.

Design characteristics:

- Dedicated Entity model
- Flow-based DAO methods
- Repository-level toggle logic
- Reactive updates

Database versioning support is included.

---

## 9. Performance Considerations

The implementation incorporates:

- Lazy rendering
- Incremental pagination
- Controlled recomposition
- Lifecycle-aware data collection

Potential production optimizations include:

- Baseline Profiles
- Macrobenchmark testing
- Startup tracing
- Memory profiling

---

## 10. Error Handling Strategy

Current implementation supports reactive error propagation.

Production enhancements may include:

- Sealed result models
- Structured UI error states
- Retry policies
- Connectivity monitoring
- Exception mapping layer

---

## 11. Security Considerations

Architecture supports:

- Dependency injection boundaries
- No hardcoded credentials
- Repository encapsulation

Production improvements may include:

- Certificate pinning
- Encrypted local database
- Secure key management
- Obfuscation and R8 hardening

---

## 12. Scalability Model

The current architecture supports:

- Feature-based modularization
- RemoteMediator integration
- Offline-first evolution
- Background synchronization
- Feature flag integration

Scaling can occur without structural redesign.

---

## 13. Testing Strategy

The structure enables:

- Use case unit testing
- Repository testing
- DAO instrumentation testing
- Flow testing
- ViewModel verification
- Compose UI testing

Future CI integration may enforce coverage and static analysis.

---

## 14. Known Limitations

The current scope excludes:

- RemoteMediator
- Full offline cache strategy
- Advanced retry mechanisms
- Analytics
- Crash reporting
- Multi-language support
- Deep linking
- Modular separation
- Complete automated test suite
- Performance benchmarking reports

All features remain architecturally feasible.

---

## 15. Conclusion

The implementation demonstrates:

- Structured architectural layering
- Reactive state modeling
- Production-aware separation of concerns
- Scalability readiness
- Maintainable system design

---

---

# 🇪🇸 Instrucciones Importantes – Configuración (Español)

La implementación activa del proyecto se encuentra en la rama `dev`.

Antes de compilar la aplicación:

1. Clonar el repositorio.
2. Cambiar a la rama `dev`:3. Abrir el proyecto en Android Studio (versión estable más reciente recomendada).
4. Sincronizar Gradle.
5. Ejecutar la aplicación en:
- Un emulador Android (API 26+ recomendado), o
- Un dispositivo físico con depuración USB activada.

Requisitos mínimos:
- Android Studio Iguana o superior
- Kotlin 1.9+
- Gradle 8+
- JDK 17

---

# 🇪🇸 Pokédex – Documentación de Arquitectura Android de Nivel Empresarial

---

## 1. Descripción General del Proyecto

Este documento describe la estructura arquitectónica, decisiones de diseño, consideraciones de escalabilidad y características de preparación para producción de la aplicación Pokédex.

La implementación sigue estándares modernos de desarrollo Android, priorizando:

- Arquitectura limpia
- Gestión reactiva del estado
- Separación clara de responsabilidades
- Diseño orientado a la testabilidad
- Preparación para escalabilidad
- Mantenibilidad a largo plazo

---

## 2. Principios Arquitectónicos

El sistema se fundamenta en:

- Inversión de dependencias
- Separación de responsabilidades
- Estado inmutable
- Flujos reactivos
- Fuente única de verdad
- Dominio independiente de la plataforma
- Mapeo explícito entre capas
- Conciencia del ciclo de vida

---

## 3. Arquitectura General

Capa de Presentación  
↓  
Capa de Dominio  
↓  
Capa de Datos  

Las dependencias fluyen hacia el interior.

---

## 4. Responsabilidades por Capa

### 4.1 Capa de Presentación

- Renderizado UI con Jetpack Compose
- Observación de estado desde ViewModels
- Emisión de interacciones
- Gestión de navegación

---

### 4.2 Capa de Dominio

- Lógica de negocio
- Contratos de repositorio
- Casos de uso
- Entidades puras

---

### 4.3 Capa de Datos

- Integración con API
- Persistencia con Room
- Gestión de paginación
- Mapeo DTO → Dominio
- Implementación de repositorio

---

## 5. Flujo de Datos

Interacción Usuario  
→ ViewModel  
→ Caso de Uso  
→ Repositorio  
→ Fuente de Datos  
→ Mapeo Dominio  
→ Emisión Flow  
→ Recomposición UI  

---

## 6. Gestión de Estado

- Estado inmutable
- Flow reactivo
- Cancelación estructurada
- Recomposición controlada

---

## 7. Paginación

Paging3 permite:

- Carga incremental
- Optimización de memoria
- Rendimiento fluido

---

## 8. Persistencia

Favoritos almacenados con Room:

- Entidades separadas
- DAO reactivo
- Versionado de base de datos

---

## 9. Rendimiento

Incluye:

- Renderizado perezoso
- Minimización de recomposición
- Observación consciente del ciclo de vida

Mejoras futuras posibles:

- Baseline Profiles
- Macrobenchmark
- Perfilado de memoria

---

## 10. Manejo de Errores

- Propagación reactiva
- Extensible a estados sellados y políticas de reintento

---

## 11. Seguridad

- Inyección de dependencias
- Encapsulación
- Preparado para cifrado y pinning de certificados

---

## 12. Escalabilidad

- Modularización futura
- Arquitectura offline-first
- Sincronización en segundo plano

---

## 13. Limitaciones Actuales

- Sin RemoteMediator
- Sin cache offline completa
- Sin analítica
- Sin crash reporting
- Sin cobertura completa de pruebas

Todas extensibles dentro de la arquitectura actual.

---

## 14. Conclusión

La aplicación refleja:

- Diseño arquitectónico estructurado
- Flujo reactivo profesional
- Separación clara de responsabilidades
- Preparación para escalabilidad
- Enfoque mantenible a largo plazo# Pokédex – Enterprise-Grade Android Architecture Documentation
Clean Architecture | Jetpack Compose | Kotlin | Coroutines | Flow | Hilt | Room | Paging3 | Material 3

---

🇬🇧 English Version  
🇪🇸 [Ir a la versión en Español](#-pokedex--documentación-de-arquitectura-android-de-nivel-empresarial)

---

# 🇬🇧 Pokédex – Enterprise-Grade Android Architecture Documentation

---

## 1. Project Overview

This document describes the architectural structure, design decisions, scalability considerations, and production-readiness characteristics of the Pokédex Android application.

The application is implemented using modern Android development standards, emphasizing:

- Clean architectural layering
- Reactive state management
- Clear separation of concerns
- Testability-oriented design
- Scalability readiness
- Maintainability over time

This documentation reflects engineering decisions at a senior architectural level.

---

## 2. Architectural Principles

The system is built upon the following principles:

- Dependency inversion
- Separation of responsibilities
- Immutable state modeling
- Reactive data streams
- Single source of truth
- Platform-independent domain logic
- Explicit mapping between layers
- Lifecycle awareness

Architectural decisions prioritize long-term maintainability and extensibility.

---

## 3. High-Level Architecture

The application follows Clean Architecture:

Presentation Layer  
↓  
Domain Layer  
↓  
Data Layer  

Dependencies flow inward. Outer layers depend on inner abstractions only.

---

## 4. Layer Responsibilities

### 4.1 Presentation Layer

Responsibilities:

- Rendering UI using Jetpack Compose
- Observing state from ViewModels
- Emitting user interactions
- Managing navigation

Characteristics:

- Stateless composables
- State hoisting
- Immutable UI state models
- Lifecycle-aware Flow collection
- Clear separation between UI and logic

No business logic is implemented within composables.

---

### 4.2 Domain Layer

Responsibilities:

- Business logic orchestration
- Definition of repository contracts
- Use case abstraction
- Pure domain modeling

Characteristics:

- Kotlin-only implementation
- No Android framework dependencies
- Fully testable in isolation
- Explicit use case classes per business operation

Domain entities are independent from API or database schemas.

---

### 4.3 Data Layer

Responsibilities:

- Remote API integration (Retrofit)
- Local persistence (Room)
- Paging data management (Paging3)
- DTO-to-domain mapping
- Repository implementation

Characteristics:

- Explicit mappers between layers
- Flow-based data emission
- Clear separation between network, database, and repository logic
- Encapsulation of data source complexity

The repository coordinates remote and local sources without exposing implementation details to upper layers.

---

## 5. Data Flow Model

User Interaction  
→ ViewModel  
→ Use Case  
→ Repository  
→ Remote / Local Data Source  
→ Domain Model Mapping  
→ Flow Emission  
→ UI Recomposition  

The system enforces unidirectional data flow.

State changes are reactive and lifecycle-aware.

---

## 6. State Management Strategy

The state management approach includes:

- Immutable UI state data classes
- Single state holder per screen
- Flow-based state exposure
- Structured coroutine scope usage
- Lifecycle-aware state collection

Compose recomposition is triggered only by state updates.

No mutable shared state is used.

---

## 7. Paging Strategy

Paging3 is used to:

- Avoid loading entire datasets into memory
- Reduce UI blocking
- Enable incremental loading
- Improve scroll performance

LazyVerticalGrid ensures efficient rendering of paged content.

Stable keys are applied to reduce unnecessary recomposition.

---

## 8. Persistence Strategy

Favorites are stored locally using Room.

Design characteristics:

- Separate Entity model
- Flow-based DAO methods
- Repository-level toggle logic
- Reactive updates across UI

Database versioning is supported.

Schema separation prevents domain leakage.

---

## 9. Performance Considerations

The application incorporates:

- Lazy rendering
- Incremental pagination
- Lifecycle-aware collection
- Controlled recomposition
- Clear separation between computation and rendering

Potential performance enhancements include:

- Baseline profile generation
- Macrobenchmark integration
- StrictMode configuration
- Startup optimization
- Memory profiling

---

## 10. Error Handling Strategy

Current implementation supports error propagation through reactive streams.

Production-ready enhancements may include:

- Sealed result models
- Explicit UI error states
- Retry policies
- Network connectivity monitoring
- Structured error mapping

---

## 11. Security Considerations

The architecture supports:

- Dependency injection boundaries
- No hardcoded secrets
- Repository encapsulation

Production-level security improvements may include:

- Certificate pinning
- Encrypted local database
- Secure API key storage
- Obfuscation strategies
- Secure network configuration

---

## 12. Scalability Model

The current architecture supports:

- Modularization by feature
- Multi-module separation
- RemoteMediator integration
- Offline-first architecture
- Background synchronization
- Feature flag integration

The separation of concerns enables scaling without major refactoring.

---

## 13. Testing Strategy

The architecture enables:

- Unit testing of use cases
- Repository testing with mocked data sources
- DAO instrumentation testing
- Flow testing
- ViewModel state verification
- Compose UI testing

Future expansion includes:

- Continuous integration enforcement
- Code coverage monitoring
- Static analysis integration

---

## 14. CI/CD Considerations

Production integration would include:

- Automated builds
- Lint enforcement
- Static code analysis
- Automated test pipelines
- Versioned release builds
- Play Store staged rollout
- Crash monitoring integration

---

## 15. Accessibility & UX Compliance

The architecture supports:

- Content descriptions
- Semantic roles
- Dynamic font scaling
- High contrast compatibility

Formal accessibility audits would validate compliance.

---

## 16. Known Limitations

The current scope excludes:

- RemoteMediator
- Full offline caching strategy
- Advanced retry mechanisms
- Analytics integration
- Crash reporting tools
- Multi-language support
- Deep linking
- Modular feature separation
- Full automated test suite
- Performance instrumentation reports

All features are structurally feasible within the current architecture.

---

## 17. Conclusion

This project demonstrates:

- Clear architectural layering
- Reactive state modeling
- Production-aware separation of concerns
- Structured data flow
- Scalability readiness
- Maintainable system design

The implementation reflects professional Android engineering standards aligned with long-term product development.

---

---

# 🇪🇸 Pokédex – Documentación de Arquitectura Android de Nivel Empresarial

[Back to English](#-pokedex--enterprise-grade-android-architecture-documentation)

---

## 1. Descripción General del Proyecto

Este documento describe la estructura arquitectónica, decisiones de diseño, consideraciones de escalabilidad y características de preparación para producción de la aplicación Pokédex.

La aplicación está implementada bajo estándares modernos de desarrollo Android, priorizando:

- Arquitectura limpia
- Gestión reactiva del estado
- Separación clara de responsabilidades
- Diseño orientado a la testabilidad
- Preparación para escalabilidad
- Mantenibilidad a largo plazo

---

## 2. Principios Arquitectónicos

El sistema se fundamenta en:

- Inversión de dependencias
- Separación de responsabilidades
- Modelado de estado inmutable
- Flujos reactivos
- Fuente única de verdad
- Dominio independiente de la plataforma
- Mapeo explícito entre capas
- Conciencia del ciclo de vida

---

## 3. Arquitectura General

La aplicación sigue Clean Architecture:

Capa de Presentación  
↓  
Capa de Dominio  
↓  
Capa de Datos  

Las dependencias fluyen hacia el interior.

---

## 4. Responsabilidades por Capa

### 4.1 Capa de Presentación

Responsabilidades:

- Renderizado UI con Jetpack Compose
- Observación de estado desde ViewModels
- Emisión de interacciones de usuario
- Gestión de navegación

Características:

- Composables sin estado
- Elevación de estado
- Modelos de estado inmutables
- Recolección de Flow consciente del ciclo de vida
- Separación estricta entre UI y lógica

---

### 4.2 Capa de Dominio

Responsabilidades:

- Orquestación de lógica de negocio
- Definición de contratos de repositorio
- Abstracción de casos de uso
- Modelado de entidades puras

Características:

- Implementación en Kotlin puro
- Sin dependencias de Android
- Totalmente testeable
- Casos de uso explícitos por operación

---

### 4.3 Capa de Datos

Responsabilidades:

- Integración con API remota
- Persistencia local con Room
- Gestión de paginación
- Mapeo DTO → Dominio
- Implementación del repositorio

Características:

- Mapeadores explícitos
- Emisión reactiva con Flow
- Separación entre red, base de datos y repositorio

---

## 5. Modelo de Flujo de Datos

Interacción Usuario  
→ ViewModel  
→ Caso de Uso  
→ Repositorio  
→ Fuente de Datos  
→ Mapeo a Dominio  
→ Emisión Flow  
→ Recomposición UI  

El flujo es unidireccional y reactivo.

---

## 6. Estrategia de Gestión de Estado

- Estado inmutable
- Fuente única de verdad
- Flow como canal reactivo
- Cancelación estructurada
- Recomposición controlada

---

## 7. Estrategia de Paginación

Paging3 permite:

- Carga incremental
- Optimización de memoria
- Rendimiento fluido
- Renderizado eficiente con LazyVerticalGrid

---

## 8. Estrategia de Persistencia

Favoritos almacenados con Room:

- Entidades separadas
- DAO reactivo
- Lógica encapsulada en repositorio
- Soporte de versionado

---

## 9. Consideraciones de Rendimiento

Incluye:

- Renderizado perezoso
- Minimización de recomposición
- Observación consciente del ciclo de vida

Mejoras futuras:

- Baseline Profiles
- Macrobenchmark
- Perfilado de memoria
- Optimización de arranque

---

## 10. Estrategia de Manejo de Errores

Actualmente:

- Propagación reactiva de errores

Expansiones posibles:

- Estados sellados de error
- Políticas de reintento
- Monitoreo de conectividad
- Mapeo estructurado de excepciones

---

## 11. Consideraciones de Seguridad

- Inyección de dependencias
- Sin secretos hardcodeados
- Encapsulación de repositorio

Mejoras posibles:

- Pinning de certificados
- Base de datos cifrada
- Protección avanzada de red

---

## 12. Modelo de Escalabilidad

Soporta:

- Modularización por feature
- Arquitectura offline-first
- Integración RemoteMediator
- Sincronización en segundo plano

---

## 13. Estrategia de Testing

Permite:

- Tests unitarios de dominio
- Tests de repositorio
- Tests DAO
- Tests UI Compose
- Integración CI futura

---

## 14. CI/CD

Preparado para:

- Build automatizado
- Linting
- Análisis estático
- Pipeline de pruebas
- Publicación controlada

---

## 15. Limitaciones Actuales

- Sin RemoteMediator
- Sin cache offline completa
- Sin analítica
- Sin crash reporting
- Sin modularización
- Sin cobertura de pruebas completa

Todas son extensibles dentro de la arquitectura actual.

---

## 16. Conclusión

La aplicación refleja:

- Diseño arquitectónico claro
- Flujo reactivo estructurado
- Separación profesional de responsabilidades
- Preparación para escalabilidad
- Enfoque mantenible a largo plazo

Representa un estándar de ingeniería Android de nivel profesional.# 📱 Pokédex – Production-Grade Android Application  
=======
# 📱 Pokédex – Production-Grade Android Application  
>>>>>>> 85f182e (Search and Favorites added)
### Clean Architecture | Jetpack Compose | Hilt | Room | Paging3 | Material 3

---

🇬🇧 **English Version**  
🇪🇸 [Ir directamente a la versión en Español](#-pokedex--aplicación-android-de-nivel-producción)

---

# 🎥 Video Demonstration

`[ VIDEO_DEMO_PLACEHOLDER – 2 to 3 minutes walkthrough ]`

---

# 🇬🇧 Pokédex – Enterprise-Grade Android Architecture Documentation

---

## 1. Project Overview

This project represents a production-oriented Android application engineered using modern architectural standards and long-term scalability principles.

The goal of this implementation is not educational replication, but architectural maturity demonstration.

The application is implemented using modern Android development standards, emphasizing:

- Clean architectural layering
- Reactive state management
- Clear separation of concerns
- Testability-oriented design
- Scalability readiness
- Maintainable over time

---

## 2. ⚠️ IMPORTANT – Setup Instructions

This project’s active implementation resides in the `dev` branch.

Before building the application:

1. Clone the repository.
2. Switch to the `dev` branch.
3. Open the project in Android Studio (Iguana or newer recommended).
4. Sync Gradle.
5. Run the application on an Android Emulator (API 26+) or a physical device.

---

## 3. Technology Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose, Material 3, Coil
- **Architecture:** Clean Architecture, MVVM, UDF
- **DI:** Hilt
- **Async:** Coroutines, Flow
- **Network:** Retrofit, OkHttp
- **Persistence:** Room
- **Paging:** Paging3
- **Testing:** JUnit4, MockK, Turbine, Compose UI Test

---

## 4. Layer Responsibilities

### 4.1 Presentation Layer
- Rendering UI using Jetpack Compose
- Observing state from ViewModels
- Managing navigation
- Characteristics: Stateless composables, State hoisting, Immutable UI state.

### 4.2 Domain Layer
- Business logic orchestration
- Definition of repository contracts
- Use case abstraction
- Characteristics: Kotlin-only, no Android dependencies, fully testable.

### 4.3 Data Layer
- Remote API integration (Retrofit)
- Local persistence (Room)
- Paging data management (Paging3)
- Characteristics: Explicit mappers, Flow-based emission, single source of truth.

---

## 5. Persistence & Offline Strategy

### Favorites (Room)
- Fully reactive persistent heart system.
- Survives app restarts.
- Reflected instantly in UI with animated scaling.

### Offline Caching (Paging3 + RemoteMediator)
- Full offline-first strategy using `RemoteMediator`.
- Pokémon list is cached locally in Room.
- Seamless user experience regardless of network connectivity.

---

## 6. Testing Strategy

The architecture is designed for 100% testability:

- **Domain Layer:** Unit tests for Use Cases using JUnit4 and MockK.
- **Data Layer:** Repository tests with in-memory Room and Fake API.
- **ViewModel:** State verification using Turbine for Flow testing.
- **UI Layer:** Compose UI tests for critical user flows (Grid, Search, Favorites).

---

## 7. Known Limitations & Roadmap

- Advanced error mapping & Retry policies.
- Certificate pinning for enhanced security.
- Analytics & Crashlytics integration.
- Full modularization by feature.
- Accessibility audit & Localization.

---

---

# 🇪🇸 Pokédex – Aplicación Android de Nivel Producción

---

## 🎥 Demostración en Video

`[ VIDEO_DEMO_PLACEHOLDER – Recorrido de 2 a 3 minutos ]`

---

## 1. Descripción General

Este proyecto representa una aplicación Android moderna, escalable y estructurada bajo principios de arquitectura limpia de nivel empresarial.

Prioriza:
- Arquitectura limpia (Clean Architecture)
- Gestión reactiva del estado
- Separación de responsabilidades
- Diseño orientado a pruebas (Testability)

---

## 2. ⚠️ Instrucciones de Configuración

La implementación activa reside en la rama `dev`.

1. Clonar el repositorio.
2. Cambiar a la rama `dev`.
3. Abrir en Android Studio (Iguana o superior).
4. Sincronizar Gradle.
5. Ejecutar en emulador (API 26+) o dispositivo físico.

---

## 3. Pilares Arquitectónicos

### Presentación
UI declarativa con Compose, ViewModels y gestión de estado inmutable.

### Dominio
Lógica de negocio pura en Kotlin, casos de uso y contratos de repositorio.

### Datos
Repositorio con estrategia de caché offline (RemoteMediator), Room y Retrofit.

---

## 4. Estrategia de Persistencia ❤️

### Favoritos
Sistema de "corazón" persistente y reactivo mediante Room. Los cambios se reflejan instantáneamente con animaciones de escala.

### Caché Offline
Implementación de `RemoteMediator` para una experiencia offline completa. Los datos se recuperan de la base de datos local cuando no hay conexión.

---

## 5. Conclusión

<<<<<<< HEAD
- Sin pruebas automatizadas
- Sin RemoteMediator
- Sin monitoreo de red
- Sin CI/CD
- Sin analítica
- Sin modularización

---

# 7. Próximas Mejoras

- Pruebas unitarias
- Pruebas UI
- Modularización
- Integración continua
- Mejoras de accesibilidad
- Optimización avanzada de caché

---

# 8. Reflexión Final

Este proyecto demuestra no solo implementación técnica,  
sino criterio arquitectónico y visión a largo plazo.

Representa un estándar profesional de desarrollo Android moderno.

---

=======
Este proyecto demuestra no solo capacidad de implementación técnica, sino madurez arquitectónica, aplicando los patrones más avanzados del ecosistema Android actual.
>>>>>>> 85f182e (Search and Favorites added)
