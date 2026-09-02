Gestor de Balnearios UTN

Este es el proyecto final de la materia Programación II de la Universidad Tecnológica Nacional, desarrollado por *Sofia Cantalupi y Franco Cancino*. Se trata de un sistema de gestión integral para balnearios que permite administrar usuarios, reservas, servicios y comprobantes de alquiler.

La aplicación permite a los clientes buscar y reservar espacios en diferentes balnearios, seleccionar servicios adicionales y generar comprobantes de sus alquileres. Por otro lado, los administradores pueden gestionar toda la información del sistema: balnearios, usuarios, servicios disponibles y consultar un historial completo de reservas.

*Tecnologías*

El proyecto utiliza Java como lenguaje principal de desarrollo, Maven como herramienta de compilación y gestión de dependencias, y JSON para la persistencia de datos. La interfaz gráfica se desarrolló con tecnologías nativas de Java, permitiendo una experiencia de usuario intuitiva y responsive.

*Instalación y Configuración*

Para ejecutar el proyecto necesitás tener instalado Java 21 o superior y Maven 3.8 o superior. Si no los tenés, descargá Java desde https://www.oracle.com/java/technologies/downloads/ y Maven desde https://maven.apache.org/download.cgi.

Una vez clonado el repositorio, navegá hasta la carpeta del proyecto:

bash
git clone https://github.com/FrancoCancino/TPFinalUTN.git
cd TPFinalUTN

Compilá el proyecto con Maven:

bash
mvn clean install

Para ejecutar la aplicación:

bash
mvn compile exec:java -Dexec.mainClass="com.balneario.Main"

O, si el proyecto está empaquetado como JAR:

bash
java -jar target/TPFinal.jar
Funcionalidades Principales

El sistema está dividido en diferentes módulos según el rol del usuario.

Los clientes pueden registrarse en la plataforma, explorar los balnearios disponibles, consultar su capacidad y servicios, y hacer reservas indicando las fechas de inicio y fin. El sistema verifica automáticamente la disponibilidad y calcula el costo total incluyendo los servicios seleccionados. Una vez confirmada la reserva, el cliente recibe un comprobante que puede descargar.

Los administradores tienen acceso a funciones de gestión completa: pueden crear y eliminar usuarios, registrar nuevos balnearios y servicios, modificar precios y disponibilidad, y consultar reportes de todas las reservas realizadas. Desde esta interfaz es posible confirmar, cancelar o modificar reservas según sea necesario.

Los empleados de los balnearios pueden ver las reservas asignadas a su turno, confirmar la llegada de los clientes, registrar servicios adicionales utilizados durante la estadía y generar comprobantes de alquiler.

Modelo de Datos

El sistema está construido alrededor de cinco entidades principales.

La entidad Usuario almacena la información de clientes y empleados del sistema, incluyendo datos de contacto y credenciales de acceso. Cada usuario está identificado de manera única y puede tener múltiples reservas asociadas.

La entidad Balneario contiene toda la información sobre los locales: nombre, ubicación, capacidad máxima y servicios que ofrece. Un balneario puede estar asociado a muchas reservas diferentes a lo largo del tiempo.

La entidad Reserva es el núcleo del sistema y vincula a un usuario con un balneario durante un período específico. Almacena las fechas de inicio y fin, los servicios solicitados, el estado de la reserva y el monto total a pagar.

La entidad Servicio representa los servicios adicionales que se pueden contratar: sombrillas, reposeras, alimentos, etc. Cada servicio tiene un precio unitario y puede ser agregado a múltiples reservas.

Finalmente, la entidad ComprobanteAlquiler registra cada transacción realizada, almacenando la fecha, el monto cobrado y los detalles de la reserva correspondiente para auditoría.

Todas estas entidades se relacionan entre sí: un usuario puede tener muchas reservas, un balneario puede tener muchas reservas, y cada reserva puede incluir varios servicios. Los comprobantes se generan una vez que una reserva es confirmada.

Estructura del Proyecto
TPFinalUTN/
├── src/main/java/com/balneario/
│   ├── model/
│   │   ├── Usuario.java
│   │   ├── Balneario.java
│   │   ├── Reserva.java
│   │   ├── Servicio.java
│   │   └── ComprobanteAlquiler.java
│   ├── controller/
│   │   ├── UsuarioController.java
│   │   ├── BalnearioController.java
│   │   └── ReservaController.java
│   ├── view/
│   │   ├── MainFrame.java
│   │   ├── RegistroPanel.java
│   │   ├── ReservaPanel.java
│   │   └── ReportePanel.java
│   ├── util/
│   │   ├── JSONManager.java
│   │   ├── ValidadorDatos.java
│   │   └── Constantes.java
│   └── Main.java
├── data/
│   ├── usuarios.json
│   ├── balnearios.json
│   ├── reservas.json
│   ├── servicios.json
│   └── ComprobateAlquiler.json
├── pom.xml
└── README.md

La carpeta model contiene las clases que representan las entidades del sistema. La carpeta controller alberga la lógica de negocio que opera sobre esos modelos. La carpeta view contiene los componentes gráficos de la aplicación. La carpeta util agrupa clases de apoyo como el gestor de archivos JSON y validadores de datos.

Los archivos de datos en formato JSON se almacenan en la carpeta data y se actualizan automáticamente a medida que se modifican usuarios, reservas y comprobantes en la aplicación.

Conceptos de Programación Orientada a Objetos

El proyecto implementa los principios fundamentales de POO en Java. La herencia se utiliza para crear una jerarquía de clases donde los usuarios específicos heredan de una clase base Usuario, permitiendo que clientes y empleados compartan comportamiento común pero tengan características distintivas.

La encapsulación se aplica en todas las entidades mediante atributos privados y métodos públicos de acceso (getters y setters), controlando cómo se modifica el estado interno de cada objeto. Esto garantiza que los datos no se corrompan y que las operaciones se realicen en un estado válido.

El polimorfismo permite que diferentes tipos de usuarios ejecuten acciones de manera distinta a través de métodos sobrecargados o sobrescritos, facilitando la extensión del sistema con nuevos roles sin modificar el código existente.

La abstracción simplifica la complejidad del sistema al ocultar los detalles internos de cómo funcionan las operaciones y exponiendo solo las interfaces necesarias para los usuarios de las clases.

Además, el proyecto hace uso extensivo de colecciones de Java como ArrayList y HashMap para manejar conjuntos de objetos, permitiendo operaciones eficientes de búsqueda, inserción y eliminación.

*Uso de la Aplicación*

Al iniciar la aplicación, se presenta una pantalla de login donde los usuarios existentes pueden iniciar sesión con sus credenciales. Si es la primera vez, es posible registrarse como nuevo cliente.

Una vez dentro del sistema, cada rol ve un menú diferente. Los clientes acceden a un listado de balnearios disponibles, donde pueden ver detalles como ubicación, capacidad e imágenes. Al seleccionar un balneario, pueden elegir las fechas de su estadía, agregar servicios opcionales y confirmar la reserva. El sistema calcula automáticamente el costo y genera un comprobante que puede descargarse.

Los administradores acceden a un panel de control con pestañas para gestionar diferentes aspectos del sistema. En la pestaña de usuarios pueden ver, crear y eliminar perfiles. En la pestaña de balnearios pueden añadir nuevos locales, modificar datos y asociar servicios. En la pestaña de reservas pueden visualizar todas las transacciones, filtrar por fecha o cliente, y generar reportes.

Los empleados ven solamente las reservas asignadas a su jornada laboral, con opciones para confirmar llegadas de clientes y registrar servicios adicionales solicitados durante la estadía.

*Persistencia de Datos*

Los datos de la aplicación se almacenan en archivos JSON en la carpeta raíz del proyecto. Esto permite que la información persista entre sesiones sin necesidad de una base de datos tradicional, facilitando el despliegue y uso del sistema.

Cada vez que se realiza una operación que modifica datos (crear un usuario, confirmar una reserva, registrar un servicio), los cambios se guardan inmediatamente en el archivo JSON correspondiente. Del mismo modo, al iniciar la aplicación, todos los archivos JSON se cargan en memoria para que estén disponibles.

Esta aproximación es simple pero escalable para los propósitos de un proyecto académico. Para un sistema en producción con muchos usuarios concurrentes, sería recomendable migrar a una base de datos relacional.
