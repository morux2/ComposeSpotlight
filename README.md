## ComposeSpotlight
Implemented Spotlight with JetpackCompose

<img src = "https://github.com/morux2/ComposeSpotlight/assets/19369161/fa9b983e-86b5-4cda-9411-c4f9f19a7878" width = 400>

### Gradle
```gradle
dependencies {
    implementation("io.github.morux2:compose-spotlight:1.0")
}
```

### Usage
```kotlin
var targetRect by remember { (mutableStateOf(Rect.Zero)) }

Spotlight(
    targetRect = targetRect,
    onTargetClicked = {},
    onDismiss = {}
)
```
