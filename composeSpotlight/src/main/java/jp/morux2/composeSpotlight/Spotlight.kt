package jp.morux2.composeSpotlight

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun Spotlight(
    targetRect: Rect,
    onTargetClicked: () -> Unit,
    onDismiss: () -> Unit,
    shape: SpotlightShape = SpotlightShape.Rect,
) {
    var showSpotlight: Boolean by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = showSpotlight,
        exit = fadeOut(tween(1000))
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { offset ->
                    if (targetRect.contains(offset)) {
                        onTargetClicked()
                    } else {
                        showSpotlight = false
                        onDismiss()
                    }
                })
            }) {

            val spotlightPath = Path().apply {
                when (shape) {
                    is SpotlightShape.Oval -> {
                        addOval(targetRect)
                    }

                    is SpotlightShape.RoundRect -> {
                        addRoundRect(
                            RoundRect(
                                rect = targetRect,
                                cornerRadius = CornerRadius(shape.radius.toPx())
                            )
                        )
                    }

                    is SpotlightShape.Rect -> {
                        addRect(targetRect)
                    }
                }
            }

            clipPath(
                path = spotlightPath,
                clipOp = ClipOp.Difference
            ) {
                drawRect(Color.Black.copy(alpha = 0.8f))
            }
        }
    }
}

sealed class SpotlightShape {
    object Oval : SpotlightShape()
    object Rect : SpotlightShape()
    data class RoundRect(val radius: Dp) : SpotlightShape()
}