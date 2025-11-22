// import './styles/globals.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

export const metadata = {
  title: 'Apartment Building',
  description: 'Building + rooms dashboard'
};

export default function RootLayout({ children }) {
  return (
    <html>
      <body>
        <main style={{ padding: 20 }}>
          <h1>Apartment Building Task</h1>
          {children}
        </main>
      </body>
    </html>
  );
}
