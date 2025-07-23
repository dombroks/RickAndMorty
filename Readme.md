# 📦 Project Package Structure (Clean Architecture + Jetpack Compose)

This project follows the **Clean Architecture** pattern with a modern, declarative UI built using **Jetpack Compose**. Below is an overview of the main packages and their responsibilities.

---

## 🧱 Package Overview

### `domain/`
- **Purpose**: Core business logic and app rules.
- **Contents**:
    - Use cases (interactors)
    - Domain models (pure Kotlin)
    - Repository interfaces
- **Notes**: Contains **no Android or Compose dependencies**. Highly testable and platform-agnostic.

---

### `data/`
- **Purpose**: Responsible for data handling and repository implementations.
- **Contents**:
    - API services (Retrofit, etc.)
    - Local data sources (Room, DataStore)
    - DTOs and mappers
    - Repository implementations
- **Notes**: Depends on both `domain` and platform-specific libraries.

---

### `presentation/`
- **Purpose**: Composable UI and screen logic.
- **Contents**:
    - Jetpack Compose screens (`@Composable` functions)
    - ViewModels (using `viewModel` or `hiltViewModel`)
    - UI state classes (`UiState`, `UiEvent`)
    - Navigation logic (if not extracted to a `navigation` package)
- **Notes**: Communicates with `domain` via use cases and observes state via `StateFlow` or `LiveData`.

---

### `design_system/`
- **Purpose**: Centralized UI design and theming.
- **Contents**:
    - App theme (`Theme.kt`, `Color.kt`, `Typography.kt`, `Shape.kt`)
    - Reusable Composables (buttons, cards, input fields)
    - Design tokens and spacing
- **Notes**: Promotes visual consistency and reusable UI components across the app.

---

### `core/`
- **Purpose**: Shared, non-UI utilities.
- **Contents**:
    - Kotlin extensions (e.g., `String.ext`, `Flow.ext`)
    - Result wrappers (e.g., `Resource`, `ResultState`)
    - Constants and generic helper functions
- **Notes**: Keep it UI-independent and lightweight.

---

### `di/`
- **Purpose**: Dependency Injection setup using Hilt.
- **Contents**:
    - Hilt modules (`NetworkModule`, `RepositoryModule`, etc.)
    - App-level or feature-level DI configuration
- **Notes**: Allows dependency configuration in a centralized and testable way.

---



## 🧩 Jetpack Compose Best Practices in This Structure

- Use `State` and `StateFlow` for **unidirectional data flow** between ViewModel and Composables.
- Place **all theme-related code** in `design_system` (including `MaterialTheme` wrappers).
- Create **reusable Composables** like `PrimaryButton`, `AppTextField` in `design_system/components/`.
- Avoid placing business logic in Composables—keep it in the ViewModel or domain layer.

---

## ✅ Benefits of This Setup

- Scalable, modular, and maintainable
- Clearly separates concerns (UI vs Logic vs Data)
- Encourages reusable, consistent UI via `design_system`
- Makes testing and debugging easier

---

## 🔄 Future Improvements

- Modularize by feature (`feature/home`, `feature/login`)
- Add `navigation/` package or module to manage screen routes centrally


