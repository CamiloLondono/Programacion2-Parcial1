# LenguajeCafetero ☕📚

Sistema de gestión para la academia de idiomas **LenguajeCafetero**, desarrollado como solución al **Parcial I de Programación II** del programa de Ingeniería de Sistemas y Computación de la Universidad del Quindío.

El sistema permite administrar estudiantes, profesores, cursos, matrículas y servicios adicionales, facilitando el control de la información y el cálculo de los valores asociados a las matrículas.

---

## 📚 Información académica

- **Universidad:** Universidad del Quindío
- **Facultad:** Ingeniería
- **Programa:** Ingeniería de Sistemas y Computación
- **Curso:** Programación II
- **Actividad:** Parcial I
- **Fecha:** 24 de septiembre de 2026

---

## 🎯 Objetivo del proyecto

Desarrollar una aplicación orientada a objetos que permita gestionar los principales procesos administrativos de la academia de idiomas LenguajeCafetero.

La aplicación busca reemplazar los registros manuales mediante un sistema que permita administrar de manera organizada:

- Estudiantes.
- Profesores.
- Cursos.
- Matrículas.
- Servicios adicionales.
- Ingresos generados durante un periodo determinado.

Además, se implementa una interfaz gráfica utilizando **JavaFX** y una arquitectura basada en el patrón **MVC (Modelo-Vista-Controlador)**.

---

## 🏫 Contexto

La academia LenguajeCafetero ofrece formación en:

- Inglés 🇬🇧
- Francés 🇫🇷
- Portugués 🇵🇹

Cuenta con diferentes modalidades de cursos:

- Cursos regulares.
- Cursos intensivos.
- Cursos personalizados.

Los estudiantes pueden matricularse en diferentes cursos durante su permanencia en la academia y adquirir servicios adicionales relacionados con sus matrículas.

---

## ⚙️ Funcionalidades principales

### 👨‍🎓 Gestión de estudiantes

El sistema permite registrar estudiantes con:

- Nombre completo.
- Documento de identidad.
- Teléfono.
- Correo electrónico.
- Edad.
- Fecha de registro.

También permite buscar un estudiante mediante su **documento de identidad**.

---

### 📚 Gestión de cursos

Cada curso contiene:

- Código.
- Nombre.
- Idioma.
- Descripción.
- Duración en meses.
- Valor mensual.
- Estado.

Los estados disponibles son:

- Activo.
- Suspendido.
- Finalizado.

El sistema contempla diferentes tipos de cursos:

- Regular.
- Intensivo.
- Personalizado.

Los cursos pueden incluir beneficios como:

- Acceso a plataforma virtual.
- Material didáctico.
- Clubes de conversación.

---

### 🎯 Cursos personalizados

Los cursos personalizados requieren información adicional:

- Cantidad de sesiones con profesor.
- Nivel de referencia.
- Objetivos del estudiante.

Los niveles contemplados son:

`A1`, `A2`, `B1`, `B2`, `C1` y `C2`.

Además, los estudiantes matriculados en cursos personalizados pueden tener un profesor asignado.

---

### 👨‍🏫 Gestión de profesores

Cada profesor contiene:

- Identificación.
- Nombre.
- Idioma que enseña.
- Teléfono.
- Tarifa por sesión.

Un profesor puede atender diferentes estudiantes.

La asignación permite establecer la relación entre:

**Estudiante → Matrícula → Curso → Profesor**

---

### 📝 Gestión de matrículas

Una matrícula relaciona al estudiante con el curso adquirido.

El valor final depende de:

- Tipo de curso.
- Duración contratada.
- Servicios adicionales.
- Descuentos establecidos por la academia.

---

### 🛠️ Servicios adicionales

El sistema permite gestionar servicios como:

- Simulacro de examen de certificación.
- Tutoría de refuerzo.
- Material impreso.
- Talleres de conversación.

Cada servicio contiene:

- Código.
- Nombre.
- Descripción.
- Precio.
- Disponibilidad.

Los servicios utilizados se relacionan con la matrícula correspondiente y se incluyen en el cálculo final del pago.

---

### 💰 Consulta de ingresos

El sistema permite consultar los ingresos generados por las matrículas realizadas dentro de un periodo determinado.

Para realizar la consulta se establece:

- Fecha inicial.
- Fecha final.

El sistema recorre las matrículas registradas, identifica las que pertenecen al periodo seleccionado y acumula sus valores totales.

---

## 🧠 Programación Orientada a Objetos

El proyecto aplica los principales conceptos de programación orientada a objetos:

- Encapsulamiento.
- Abstracción.
- Herencia.
- Polimorfismo.
- Composición y asociación.
- Responsabilidad de las clases.

El diseño busca representar las entidades principales del dominio mediante clases con responsabilidades claramente definidas.

---

## 🧱 Principios SOLID

Durante la implementación se aplican principios SOLID para mantener un código organizado, mantenible y con responsabilidades separadas.

### S — Single Responsibility Principle

Cada clase debe tener una responsabilidad específica.

Por ejemplo, las clases del dominio representan entidades del sistema, mientras que las clases encargadas de la lógica de aplicación gestionan las operaciones correspondientes.

### O — Open/Closed Principle

La estructura permite extender el sistema con nuevos tipos de cursos o servicios sin modificar innecesariamente las funcionalidades existentes.

### L — Liskov Substitution Principle

Las clases derivadas pueden utilizarse donde se espera su clase base sin alterar el comportamiento esperado del sistema.

### I — Interface Segregation Principle

Las interfaces se mantienen enfocadas en responsabilidades específicas para evitar dependencias innecesarias.

### D — Dependency Inversion Principle

Las responsabilidades de alto nivel dependen de abstracciones en lugar de depender directamente de implementaciones concretas.

---

## 🏭 Patrón creacional

El proyecto utiliza un patrón creacional para controlar la creación de objetos y facilitar la extensión del sistema.

### Factory Method / Simple Factory

La creación de los diferentes tipos de cursos puede centralizarse mediante una fábrica.

Por ejemplo:

```text
CursoFactory
    │
    ├── CursoRegular
    ├── CursoIntensivo
    └── CursoPersonalizado
```

Esto permite crear diferentes tipos de cursos sin que el código cliente tenga que conocer directamente todos los detalles de construcción de cada clase.

---

## 🖥️ Interfaz gráfica

La aplicación utiliza **JavaFX** para proporcionar una interfaz gráfica que permita al personal encargado interactuar con el sistema.

Entre las operaciones principales se encuentran:

- Registrar estudiantes.
- Consultar estudiantes.
- Gestionar cursos.
- Registrar profesores.
- Crear matrículas.
- Asociar servicios adicionales.
- Consultar ingresos.

---

## 🏗️ Arquitectura MVC

El proyecto utiliza el patrón arquitectónico **Modelo-Vista-Controlador (MVC)**.

```text
                 ┌─────────────────┐
                 │      VISTA      │
                 │    JavaFX/FXML  │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │   CONTROLADOR   │
                 │    Controller   │
                 └────────┬────────┘
                          │
                          ▼
                 ┌─────────────────┐
                 │     MODELO      │
                 │ Entidades/Lógica│
                 └─────────────────┘
```

### Modelo

Contiene las entidades y reglas relacionadas con el dominio:

- Estudiante.
- Profesor.
- Curso.
- Matrícula.
- Servicio adicional.
- Academia.

### Vista

Contiene los elementos visuales de la aplicación desarrollados con JavaFX.

### Controlador

Recibe las acciones realizadas por el usuario desde la interfaz y coordina las operaciones con el modelo.

---

## 📁 Estructura del proyecto

Una posible organización del proyecto es:

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── lenguajecafetero/
    │           ├── model/
    │           │   ├── Academia.java
    │           │   ├── Estudiante.java
    │           │   ├── Profesor.java
    │           │   ├── Curso.java
    │           │   ├── CursoRegular.java
    │           │   ├── CursoIntensivo.java
    │           │   ├── CursoPersonalizado.java
    │           │   ├── Matricula.java
    │           │   └── ServicioAdicional.java
    │           │
    │           ├── controller/
    │           │   ├── EstudianteController.java
    │           │   ├── CursoController.java
    │           │   ├── MatriculaController.java
    │           │   └── MainController.java
    │           │
    │           ├── factory/
    │           │   └── CursoFactory.java
    │           │
    │           ├── service/
    │           │   └── ...
    │           │
    │           └── Main.java
    │
    └── resources/
        └── com/
            └── lenguajecafetero/
                ├── views/
                │   ├── main-view.fxml
                │   ├── estudiantes-view.fxml
                │   ├── cursos-view.fxml
                │   └── matriculas-view.fxml
                │
                └── styles/
                    └── styles.css
```

> La estructura puede variar de acuerdo con la implementación final del proyecto.

---

## 🛠️ Tecnologías utilizadas

- **Java**
- **JavaFX**
- **FXML**
- **CSS**
- **Programación Orientada a Objetos**
- **UML**
- **MVC**
- **SOLID**
- **Patrones de diseño**

---

## 🔎 Principales consultas

El sistema contempla principalmente:

### Buscar estudiante

Permite localizar un estudiante mediante su documento de identidad.

```text
Documento → Buscar → Estudiante
```

### Consultar ingresos

Permite seleccionar un periodo:

```text
Fecha inicial ───────── Fecha final
        │
        ▼
   Matrículas
        │
        ▼
Filtrar por fecha
        │
        ▼
Acumular valores
        │
        ▼
Ingresos totales
```

---

## 👥 Integrantes

| Integrante                      | Código     |
| ------------------------------- |------------|
| Cristian Camilo Londoño Álvarez | 1115195149 |
| Elisabet Arcila Marulanda       | 1094883329 |

---

## 📹 Sustentación

El proyecto cuenta con un video de sustentación en el que se presenta:

- Análisis del problema.
- Diagrama de clases.
- Implementación en Java.
- Principios SOLID utilizados.
- Patrón creacional implementado.
- Arquitectura MVC.
- Interfaz JavaFX.
- Funcionamiento de las principales funcionalidades.

**Video:** [Agregar enlace de YouTube]

---

## 📄 Documentación

La documentación del proyecto incluye:

- Análisis del problema.
- Identificación de elementos del dominio.
- Reglas de negocio.
- Diagrama UML.
- Explicación de la solución.
- Decisiones de diseño.

---

## 🚀 Ejecución del proyecto

Para ejecutar el proyecto se requiere:

1. Tener instalado **Java**.
2. Tener configurado **JavaFX**.
3. Clonar el repositorio.
4. Abrir el proyecto desde el IDE.
5. Configurar las dependencias necesarias.
6. Ejecutar la clase principal `Main.java`.

---

## 📌 Estado del proyecto

Proyecto académico desarrollado para el **Parcial I de Programación II**.

El sistema se encuentra orientado a demostrar conocimientos de:

**Programación Orientada a Objetos + SOLID + Patrones Creacionales + JavaFX + MVC**

---

## 👨‍💻 Autores

**Cristian Camilo Londoño Álvarez**
Ingeniería de Sistemas y Computación
Universidad del Quindío

**Elisabet Arcila Marulanda**
Ingeniería de Sistemas y Computación
Universidad del Quindío
