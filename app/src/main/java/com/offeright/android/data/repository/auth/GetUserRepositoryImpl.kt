package com.offeright.android.data.repository.auth
import com.offeright.android.data.datasource.local.db.AppDatabase
import com.offeright.android.data.datasource.remote.NetworkService
import com.offeright.android.data.model.base.APIError
import com.offeright.android.data.model.response.DataResult
import com.offeright.android.domain.model.User
import com.offeright.android.domain.model.UserDetail
import com.offeright.android.domain.repository.auth.GetUserRepository
import com.offeright.android.util.mapToAPIError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val networkService: NetworkService
): GetUserRepository {
    override fun getLocalUser(): Flow<DataResult<User>> {
        return flow {
            try {
                val response = appDatabase.userDAO().getUsers().first()
                emit(DataResult.Success(response.toModel()))
            } catch (e: HttpException) {
                emit(DataResult.Error(e.mapToAPIError()))
            } catch (e: Exception) {
                emit(DataResult.Error(e.mapToAPIError()))
            }
        }
    }

    override fun fetchRemoteUser(): Flow<DataResult<UserDetail>> {
        return flow {
            try {
                val response = networkService.getUserDetail()
                if (response.errors == null) {
                    emit(DataResult.Success(response.data?.toModel() ?: UserDetail()))
                } else {
                    val message = response.message ?: "Unknown error"
                    val error = APIError(message = message, code = response.responseCode)
                    emit(DataResult.Error(error))
                }
            } catch (e: HttpException) {
                emit(DataResult.Error(e.mapToAPIError()))
            } catch (e: Exception) {
                emit(DataResult.Error(e.mapToAPIError()))
            }
        }
    }
}
