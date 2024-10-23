package com.adyen.android.assignment.di

import android.content.Context
import com.adyen.android.assignment.common.exceptions.errorhandler.ErrorHandler
import com.adyen.android.assignment.common.exceptions.errorhandler.HttpExceptionHandler
import com.adyen.android.assignment.common.exceptions.errorhandler.SocketTimeoutExceptionHandler
import com.adyen.android.assignment.common.exceptions.errorhandler.UnknownExceptionHandler
import com.adyen.android.assignment.common.exceptions.errorhandler.UnknownHostExceptionHandler
import com.adyen.android.assignment.common.exceptions.exceptionconverter.ExceptionConverter
import com.adyen.android.assignment.common.exceptions.exceptionconverter.HttpCallFailureExceptionConverter
import com.adyen.android.assignment.common.exceptions.exceptionconverter.NoNetworkExceptionConverter
import com.adyen.android.assignment.common.exceptions.exceptionconverter.ServerUnreachableExceptionConverter
import com.adyen.android.assignment.common.exceptions.exceptionconverter.UnknownExceptionConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ErrorHandlerModule {

    @Provides
    fun provideErrorHandle(): ErrorHandler = SocketTimeoutExceptionHandler(
        UnknownHostExceptionHandler(
            HttpExceptionHandler(
                UnknownExceptionHandler()
            )
        )
    )

    @Provides
    fun provideExceptionConverter(@ApplicationContext context: Context): ExceptionConverter =
        NoNetworkExceptionConverter(
            ServerUnreachableExceptionConverter(
                HttpCallFailureExceptionConverter(
                    UnknownExceptionConverter(
                        context
                    ),
                    context
                ),
                context
            ),
            context
        )
}
