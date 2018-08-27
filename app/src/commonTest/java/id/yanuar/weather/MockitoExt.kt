package id.yanuar.weather

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
inline fun <reified T> mock() = Mockito.mock(T::class.java)

inline fun <reified T> argumentCaptor() = ArgumentCaptor.forClass(T::class.java)