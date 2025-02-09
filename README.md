# 🚔 Aplicación de Gestión de Multas

Esta aplicación permite gestionar multas a través de un sistema basado en **Java, JSP y Servlets**, utilizando **MySQL** como base de datos y desplegado en un servidor **Tomcat**. Los usuarios pueden registrarse como **usuarios** o **representantes**. Los representantes acceden a la información de sus representados y los administradores tienen permisos para eliminar multas.

## 🚀 Características
- 📝 **Registro de usuarios y representantes**.
- 🔍 **Visualización de multas** con detalles como razón y monto.
- 🔐 **Acceso restringido** según el tipo de usuario:
  - Los **representantes** solo pueden ver las multas de sus representados.
  - Los **usuarios** pueden ver sus propias multas.
  - Los **administradores** pueden **eliminar multas**.

## 🛠️ Tecnologías utilizadas
- **Lenguaje**: Java (JSP y Servlets)
- **Base de datos**: MySQL
- **Servidor de aplicaciones**: Apache Tomcat
- **Frontend**: HTML, CSS, JavaScript
- **Gestión de sesiones**: Cookies y HttpSession

## 📌 Instalación
1️⃣ **Clona el repositorio en tu entorno local:**
```bash
   git clone https://github.com/tuusuario/gestion_multas_Java_mysql.git
```

2️⃣ **Importa la base de datos en MySQL:**
   - Accede a MySQL y crea la base de datos:
   ```sql
   CREATE DATABASE gestion_multas_Java_mysql;
   ```
   - Importa el archivo SQL con la estructura y datos iniciales:
   ```bash
   mysql -u usuario -p gestion_multas_Java_mysql < database.sql
   ```

3️⃣ **Configura la conexión a MySQL en `config.properties`:**
   ```properties
   db.url=jdbc:mysql://localhost:3306/gestion_multas
   db.user=tu_usuario
   db.password=tu_contraseña
   ```

4️⃣ **Configura y despliega el proyecto en Tomcat:**
   - Copia el proyecto en la carpeta `webapps` de Tomcat.
   - Inicia Tomcat y accede a:
   ```
   http://localhost:8080/gestion_multas_Java_mysql/
   ```

## ▶️ Uso
1️⃣ **Registro de usuario o representante.**
2️⃣ **Inicio de sesión con credenciales.**
3️⃣ **Visualización de multas asociadas.**
4️⃣ **Eliminación de multas (solo administradores).**

## 🏗️ Funcionalidades
- 🏛 **Gestión de usuarios y representantes.**
- 🚦 **Consulta y administración de multas.**
- 🔑 **Diferenciación de roles con acceso restringido.**

## 📜 Licencia
Este proyecto está bajo la licencia MIT.

---
💻 **Autor:** [Roy Duran](https://github.com/RoyDuran)

