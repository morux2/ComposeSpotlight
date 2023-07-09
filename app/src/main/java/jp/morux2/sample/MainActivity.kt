package jp.morux2.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.morux2.composeSpotlight.Spotlight
import jp.morux2.composeSpotlight.SpotlightShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var targetRect by remember { (mutableStateOf<Rect?>(null)) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                Target(
                    modifier = Modifier.onGloballyPositioned {
                        targetRect = it.boundsInRoot()
                    }
                )
                targetRect?.let {
                    Spotlight(
                        targetRect = it,
                        shape = SpotlightShape.RoundRect(16.dp),
                        onTargetClicked = {
                            Toast.makeText(
                                this@MainActivity,
                                "Target Clicked !!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onDismiss = {}
                    )
                }
            }
        }
    }
}

@Composable
fun Target(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Android!",
        fontSize = 48.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TargetPreview() {
    Target()
}