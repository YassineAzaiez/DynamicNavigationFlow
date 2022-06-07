package com.skoove.shared.commundomain

interface BaseUseCase<out Type> {
    suspend operator fun invoke(): Type
}