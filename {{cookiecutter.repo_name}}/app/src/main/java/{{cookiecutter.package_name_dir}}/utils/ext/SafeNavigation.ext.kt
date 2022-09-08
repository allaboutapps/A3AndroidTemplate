package {{cookiecutter.package_name}}.utils.ext

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@IdRes
private fun NavController.getDestinationIdFromAction(@IdRes actionId: Int): Int? {
    val action: NavAction? = (currentDestination?.getAction(actionId) ?: graph.getAction(actionId))
    return action?.destinationId
}

@MainThread
fun NavController.navigateSafe(
    directions: NavDirections,
    navOptions: NavOptions? = null,
) {
    @IdRes
    val destinationId: Int? = this.getDestinationIdFromAction(directions.actionId)

    if (destinationId != null && currentDestination?.id != destinationId) {
        navigate(directions, navOptions)
    }
}

@MainThread
fun NavController.navigateSafe(
    directions: NavDirections,
    navigatorExtras: Navigator.Extras,
) {
    @IdRes
    val destinationId: Int? = this.getDestinationIdFromAction(directions.actionId)

    if (destinationId != null && currentDestination?.id != destinationId) {
        navigate(directions, navigatorExtras)
    }
}

@MainThread
fun NavController.navigateSafe(
    @IdRes resId: Int,
    @IdRes startingDestinationId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null,
) {
    if (currentDestination?.id == startingDestinationId) {
        navigate(resId, args, navOptions, navExtras)
    }
}

@MainThread
fun NavController.popBackStackSafe(@IdRes startingDestinationId: Int): Boolean {
    if (currentDestination?.id == startingDestinationId) {
        return popBackStack()
    }

    return false
}

@MainThread
fun NavController.popBackStackSafe(
    @IdRes destinationId: Int,
    @IdRes startingDestinationId: Int,
    inclusive: Boolean,
    saveState: Boolean = false,
): Boolean {
    if (currentDestination?.id == startingDestinationId) {
        return popBackStack(destinationId, inclusive, saveState)
    }

    return false
}
