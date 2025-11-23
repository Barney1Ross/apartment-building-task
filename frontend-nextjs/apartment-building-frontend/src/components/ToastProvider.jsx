"use client";
import { Toast } from 'primereact/toast';
import { useRef, useEffect } from 'react';

export default function ToastProvider() {
  const toast = useRef(null);
  useEffect(() => { globalThis.appToast = toast; }, []);
  return <Toast ref={toast} position="top-right" />;
}
