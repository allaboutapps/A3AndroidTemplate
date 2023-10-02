package {{ cookiecutter.package_name }}.features.start

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.appcompat.app.AppCompatActivity
import {{ cookiecutter.package_name }}.theme.AppTheme
import androidx.lifecycle.viewmodel.compose.viewModel as composeViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Content()
            }
        }
    }
}

@Composable
private fun Content(viewModel: MainViewModel = composeViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        GreetingView(viewModel.greeting)
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        GreetingView("Hello, Android!")
    }
}
