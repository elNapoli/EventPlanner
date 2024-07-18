package com.baldomeronapoli.eventplanner.android.views.auth

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.baldomeronapoli.eventplanner.android.R
import com.baldomeronapoli.eventplanner.android.components.DetailComponent
import com.baldomeronapoli.eventplanner.android.components.NButton
import com.baldomeronapoli.eventplanner.android.components.NPreview
import com.baldomeronapoli.eventplanner.android.views.base.EmptyScaffold

@Composable
fun SuccessfulSignUpScreen(modifier: Modifier = Modifier) {
    EmptyScaffold(
        modifier = modifier
    ){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, detail, button) = createRefs()


            Image(
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top, margin = 64.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                painter = painterResource(id = R.drawable.congrats),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            DetailComponent(
                modifier = Modifier.constrainAs(detail) {
                    top.linkTo(image.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                title = stringResource(id = R.string.congrats),
                description = stringResource(id = R.string.congrats_description)
            )
            NButton(modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, text = stringResource(id = R.string.login_button)) {

            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewSuccessfulSignUpScreenLight(modifier: Modifier = Modifier) {
    NPreview {
        SuccessfulSignUpScreen()
    }
}