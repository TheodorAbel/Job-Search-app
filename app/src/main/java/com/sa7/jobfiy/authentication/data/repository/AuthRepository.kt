package com.sa7.jobfiy.authentication.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("No account found with this email. Please sign up first."))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception("Invalid password. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception("Login failed: ${e.message}"))
        }
    }

    fun getCurrentUser() = auth.currentUser

    fun isUserLoggedIn() = auth.currentUser != null

    fun logout() {
        auth.signOut()
    }

    suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("No account found with this email."))
        } catch (e: Exception) {
            Result.failure(Exception("Failed to send reset email: ${e.message}"))
        }
    }
} 