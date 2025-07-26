# Project Package Structure

This project follows the **Clean Architecture** pattern with a modern, declarative UI built using *
*Jetpack Compose and MVI**. Below is an overview of the main packages and their responsibilities.

---

## Project Overview

### `domain/`

- **Purpose**: Core business logic and app rules.
- **Contents**:
    - Use cases (interactors)
    - Domain models (pure Kotlin)
    - Repository interfaces
- **Description**: Contains **no Android or Compose dependencies**. Highly testable and platform-agnostic.

---

### `data/`

- **Purpose**: Responsible for data handling and repository implementations.
- **Contents**:
    - API services (Retrofit, etc.)
    - Local data sources (Room, DataStore)
    - DTOs and mappers
    - Repository implementations
- **Description**: Depends on both `domain` and platform-specific libraries.

---

### `presentation/`

- **Purpose**: Composable UI and screen logic.
- **Contents**:
    - Jetpack Compose screens (`@Composable` functions)
    - ViewModels (using `viewModel` or `hiltViewModel`)
    - UI state classes (`UiState`, `UiEvent`)
    - Navigation logic (if not extracted to a `navigation` package)
- **Description**: Communicates with `domain` via use cases and observes state via `StateFlow`.

---

### `design_system/`

- **Purpose**: Centralized UI design and theming.
- **Contents**:
    - App theme (`Theme.kt`, `Color.kt`, `Typography.kt`)
    - Reusable Composables (buttons, cards, input fields)
- **Description**: Visual consistency and reusable UI components across the app.

---

### `core/`

- **Purpose**: Shared, non-UI utilities.
- **Contents**:
    - Kotlin extensions
    - Constants and generic helper functions
- **Description**: Shared stuff.

---

### `di/`

- **Purpose**: Dependency Injection setup using Hilt.
- **Contents**:
    - Hilt modules (`NetworkModule`, `RepositoryModule`, etc.)
    - App-level or feature-level DI configuration
- **Description**: Allows dependency configuration in a centralized and testable way.

---


## Benefits of This Setup

- Scalable, modular, and maintainable
- Clearly separates concerns (UI vs Logic vs Data)
- Encourages reusable, consistent UI via `design_system`
- Makes testing and debugging easier

---

## Future Improvements

- Modularization
- Code quality setup: ktlint, detekt, konsist, and spotless:
    - [ktlint](https://github.com/pinterest/ktlint): Enforces Kotlin code style rules.
    - [spotless](https://github.com/diffplug/spotless): Runs ktlint to auto-format code
      consistently.
    - [detekt](https://github.com/detekt/detekt): Performs static code analysis to catch code smells
      and potential bugs.
    - [konsist](https://github.com/LemonAppDev/konsist): Validates architecture and layering rules
      to enforce project structure.

        - Benefits of This Setup:
          - Enforces consistent code formatting across the entire codebase.
          - Detects common bugs, bad practices, and complexity issues early.
          - Maintains a clean and scalable architecture by enforcing layering rules.
          - Reduces manual effort in code reviews by automating style and structure checks.
          - Scales with team and project growth, keeping code maintainable over time.
          - Makes onboarding easier with clear, enforced coding standards.
          - Prevents architecture drift and encourages separation of concerns.
          - Fully customizable to match team's standards and guidelines.


