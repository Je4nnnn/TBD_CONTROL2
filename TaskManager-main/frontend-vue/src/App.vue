<script setup>
import { computed } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const isAuthenticated = computed(() => !!localStorage.getItem('token'));
const username = computed(() => localStorage.getItem('username') || '');
const role = computed(() => localStorage.getItem('role') || '');

const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('userId');
  localStorage.removeItem('username');
  localStorage.removeItem('role');
  router.push({ name: 'login' });
};
</script>

<template>
  <div class="app-container">
    <header class="app-header">
      <h1>Sistema de Gestión de Tareas</h1>
      <nav v-if="isAuthenticated">
        <span class="user-label">
          Hola, {{ username }} <span v-if="role">({{ role }})</span>
        </span>

        <router-link to="/tasks">Mis tareas</router-link>

        <!-- NUEVO BOTÓN -->
        <router-link to="/preguntas">Preguntas</router-link>

        <router-link v-if="role === 'ADMIN'" to="/admin">Admin</router-link>
        <button @click="logout">Cerrar sesión</button>
      </nav>
    </header>

    <main class="app-main">
      <router-view />
    </main>
  </div>
</template>

<!-- Estilos (mantener los existentes o agregar para el nav) -->
<style>
/* Asegura que el nav se vea bien */
nav a {
  margin-right: 15px;
  text-decoration: none;
  color: #ccc;
  font-weight: bold;
}
nav a:hover { color: white; }
nav a.router-link-active { color: #41b883; }
</style>