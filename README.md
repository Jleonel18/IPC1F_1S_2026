# IPC1F_1S_2026
Repositorio de Ejemplos de Primer Semestre de IPC1

# Guía básica de Git trabajando en la rama main

## 1. Requisitos previos

Antes de comenzar, los estudiantes deben:

1. Tener Git instalado.
2. Tener una cuenta en GitHub.
3. Haber creado un repositorio en GitHub (vacío o con README).
4. Haber generado un Personal Access Token (PAT) en GitHub.

---

# 2. Autenticación con Token (Personal Access Token)

GitHub ya no permite usar contraseña al hacer `push` o `pull`.

Se debe usar un Personal Access Token.

## Crear un token en GitHub

1. Ir a GitHub.
2. Settings.
3. Developer Settings.
4. Personal Access Tokens.
5. Tokens (classic) → Generate new token.
6. Dar permisos al menos de tipo `repo`.
7. Generar y copiar el token (solo se muestra una vez).

Guárdalo en un lugar seguro.

---

# 3. Configuración inicial (solo se hace una vez por computadora)

Configurar nombre y correo:

```bash
git config --global user.name"Tu Nombre"
git config --global user.email"tuemail@example.com"
```

Opcional: guardar credenciales para no pegar el token cada vez:

```bash
git config --global credential.helper store
```

---

# 4. Clonar un repositorio

Este paso se hace una sola vez.

```bash
gitclone https://github.com/usuario/repositorio.git
```

Entrar a la carpeta:

```bash
cd repositorio
```

Verificar que estamos en main:

```bash
git branch
```

Si no está en main:

```bash
git checkout main
```

---

# 5. Ver el estado del repositorio

```bash
git status
```

Este comando muestra:

- Archivos modificados
- Archivos nuevos
- Archivos listos para commit
- Rama actual

Es el comando más importante para entender qué está pasando.

---

# 6. Agregar archivos al área de preparación

Agregar un archivo específico:

```bash
git add archivo.js
```

Agregar todos los cambios:

```bash
git add .
```

Esto no guarda el cambio en GitHub, solo lo prepara para el commit.

---

# 7. Crear un commit

```bash
git commit -m"Mensaje descriptivo del cambio"
```

Ejemplo:

```bash
git commit -m"Agrega validación al formulario de registro"
```

El commit guarda los cambios localmente, aún no están en GitHub.

---

# 8. Traer cambios del repositorio remoto

Antes de subir cambios, es buena práctica ejecutar:

```bash
git pull origin main
```

Esto descarga los cambios más recientes del repositorio remoto.

Si hay conflictos, deben resolverse antes de continuar.

---

# 9. Subir cambios a GitHub

```bash
git push origin main
```

Cuando lo solicite:

- Username: tu usuario de GitHub
- Password: pega el token

Si configuraste `credential.helper store`, solo lo pedirá una vez.

---

# 10. Flujo de trabajo recomendado en main

Cada vez que trabajen:

```bash
git status
git add .
git commit -m"Descripción del cambio"
git pull origin main
git push origin main
```

Orden recomendado:

1. Ver estado.
2. Agregar cambios.
3. Hacer commit.
4. Hacer pull.
5. Hacer push.

---

# 11. Error común al hacer push

Si aparece:

```
error: failedto push some refs
```

Significa que el repositorio remoto tiene cambios que tú no tienes.

Solución:

```bash
git pull origin main
```

Resolver conflictos si aparecen, luego:

```bash
git push origin main
```

---

# Resumen conceptual:

- `clone`: descarga el proyecto por primera vez.
- `status`: muestra qué cambió.
- `add`: prepara cambios.
- `commit`: guarda cambios localmente.
- `pull`: trae cambios del servidor.
- `push`: sube cambios al servidor.