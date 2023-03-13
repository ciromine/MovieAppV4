# MovieAppV4

Aplicación simple obteniendo datos de https://api.themoviedb.org
La arquitectura utlizada es Clean Architecture con MVVM
El lenguaje utilizado es Kotlin.
Incluye unit tests.

Min SDK 21 y Target SDK 32

# Library Stack
* Hilt: Para inyección de dependencias
* Coroutines + Flow: Para manejo de multithreads
* Retrofit/OkHttp/Gson: Para consumir servicios
* View binding: Para manejar los componentes visuales.
* Navigation Component: Para implementar la navegación entre vistas.

# Unit Test
junit y mockk.
