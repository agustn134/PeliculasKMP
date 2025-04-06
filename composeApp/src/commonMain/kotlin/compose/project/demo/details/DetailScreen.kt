import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import compose.project.demo.Movie
import compose.project.demo.details.DetailViewModel
import org.jetbrains.compose.resources.stringResource
import peliculaskmp.composeapp.generated.resources.Res
import peliculaskmp.composeapp.generated.resources.back


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {
//    val state= vm.state
//    Screen {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text(state.movie?.title ?:"") },
//                    navigationIcon = {
//                        IconButton(onClick = onBack) {
//                            Icon(
//                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
//                                contentDescription = stringResource(Res.string.back)
//                            )
//                        }
//                    }
//                )
//            }
//        ) { padding ->
//            if (state.loading) {
//                Box(
//                    modifier = Modifier.fillMaxSize()
//                        .padding(padding),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
//            state.movie?.let { movie ->
//                Column(
//                    modifier = Modifier
//                        .padding(padding)
//                        .verticalScroll(rememberScrollState())
//                ) {
//                    AsyncImage(
//                        model = movie.poster,
//                        contentDescription = movie.title, // Aquí debe ir el texto descriptivo, no una escala
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .aspectRatio(16f / 9f)
//                    )
//                    Text(
//                        text = movie.title,
//                        modifier = Modifier.padding(16.dp),
//                     //   style = MaterialTheme.typography.headlineMedium
//                    )
//                    Text(
//                        text = buildAnnotatedString {
//                            property("Title", movie.title)
//                            property("Original language", movie.originalLanguage)
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
//                            .padding(16.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//private fun AnnotatedString.Builder.property(name: String, value: String){
//    withStyle(ParagraphStyle(lineHeight = 18.sp)){
//        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
//            append("$name: ")
//        }
//        append(value)
//    }
//}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {
    val state = vm.state
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(state.movie?.title ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                )
            }
        ) { padding ->
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.movie?.let { movie ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Poster Image
                    AsyncImage(
                        model = movie.poster,
                        contentDescription = "Poster of ${movie.title}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2 / 3f)
                    )

                    // Movie Details
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Title
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Basic Info Row
                        Row(
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            // Rating Chip
                            Box(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .background(MaterialTheme.colorScheme.primaryContainer)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "★ ${movie.voteAverage?.toString()?.take(3) ?: "N/A"}",
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Language Chip
                            Box(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = movie.originalLanguage.uppercase(),
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }

                        // Overview
                        Text(
                            text = "Overview",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = movie.overview,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Additional Details
                        Column {
                            propertyDetail("Original Title", movie.originalTitle)
                            propertyDetail("Release Date", movie.releaseDate ?: "Unknown")
                            propertyDetail("Popularity", movie.popularity.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.propertyDetail(name: String, value: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}




