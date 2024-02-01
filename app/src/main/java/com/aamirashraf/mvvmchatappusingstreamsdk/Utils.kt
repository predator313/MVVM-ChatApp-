package com.aamirashraf.mvvmchatappusingstreamsdk

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

const val MIN_USERNAME_LEN=3

fun NavController.navigateSafely(
    @IdRes resId:Int,
    args:Bundle?=null,
    navOptions: NavOptions?=null,
    navExtras:Navigator.Extras?=null

){
    val action=currentDestination?.getAction(resId)?:graph.getAction(resId)
    if(action!=null && currentDestination?.id!=action.destinationId){
        navigate(resId,args,navOptions,navExtras)
    }
}