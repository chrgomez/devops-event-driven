import { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [formData, setFormData] = useState({ nombre: '', proyectoId: '' });
  const [status, setStatus] = useState(null);
  const [stats, setStats] = useState(0);

  // Consultar estadísticas cada 3 segundos
  useEffect(() => {
    const fetchStats = async () => {
      try {
        const res = await fetch('http://localhost:8082/stats');
        const data = await res.json();
        setStats(data.total_tareas_procesadas);
      } catch (e) { console.log("Esperando estadísticas..."); }
    };
    const interval = setInterval(fetchStats, 3000);
    return () => clearInterval(interval);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8081/api/tareas?nombre=${formData.nombre}&proyectoId=${formData.proyectoId}`, { method: 'POST' });
      setStatus(response.ok ? {type: 'success', msg: 'Evento publicado en Kafka'} : {type: 'error', msg: 'Error backend'});
    } catch (err) { setStatus({type: 'error', msg: 'Error de red'}); }
  };

  return (
    <div className="container">
      <h1>DevOps Platform</h1>
      <p className="subtitle">Sistema Event-Driven con Kafka</p>

      {/* Dashboard de Métricas */}
      <div style={{ background: '#0f172a', padding: '1rem', borderRadius: '1rem', marginBottom: '2rem', border: '1px solid #3b82f6', textAlign: 'center' }}>
        <h3 style={{ color: '#60a5fa', margin: 0, fontSize: '0.8rem' }}>Tareas Procesadas</h3>
        <p style={{ fontSize: '2.5rem', fontWeight: '900', margin: '0.5rem 0' }}>{stats}</p>
      </div>

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Nombre de Tarea</label>
          <input onChange={(e) => setFormData({...formData, nombre: e.target.value})} placeholder="Ej: CI/CD Pipeline" />
        </div>
        <div className="form-group">
          <label>Proyecto ID</label>
          <input onChange={(e) => setFormData({...formData, proyectoId: e.target.value})} placeholder="Ej: PROJ-99" />
        </div>
        <button type="submit">Publicar Evento</button>
      </form>

      {status && <div style={{marginTop: '20px', textAlign: 'center', color: status.type === 'success' ? '#34d399' : '#f87171'}}>{status.msg}</div>}

      <div className="footer">
        <p>Grupo 1 - DevOps Engineering @2026</p>
      </div>
    </div>
  );
}
export default App;