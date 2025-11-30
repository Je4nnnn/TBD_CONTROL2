import api from './api';

const taskService = {
  async getSectors() {
    const response = await api.get('/sectors');
    return response.data;
  },

  async getTasks() {
    const userId = Number(localStorage.getItem('userId'));
    const response = await api.get('/tasks', {
      params: { userId },
    });
    return response.data;
  },

  async createTask(task, targetUserId = null) {
    const userId = targetUserId || Number(localStorage.getItem('userId'));
    const payload = { ...task, userId };
    const response = await api.post('/tasks', payload);
    return response.data;
  },

  // MODIFICADO: Ahora respetamos el userId que viene dentro del objeto 'task'
  // o permitimos pasarlo explícitamente.
  async updateTask(id, task) {
    // Si el objeto task ya trae un userId (porque lo seleccionamos en el dropdown del admin), lo usamos.
    // Si no, usamos el del usuario logueado.
    const currentUserId = Number(localStorage.getItem('userId'));
    const payload = {
        ...task,
        userId: task.userId || currentUserId
    };

    const response = await api.put(`/tasks/${id}`, payload);
    return response.data;
  },

  async deleteTask(id) {
    await api.delete(`/tasks/${id}`);
  },
};

export default taskService;