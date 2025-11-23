// lightweight wrappers that use globalThis.appToast
export function showSuccess(msg) {
  try { globalThis.appToast?.current?.show({ severity: 'success', summary: 'Success', detail: msg, life: 2000 }); }
  catch (e) { }
}
export function showError(msg) {
  try { globalThis.appToast?.current?.show({ severity: 'error', summary: 'Error', detail: String(msg), life: 3500 }); }
  catch (e) { }
}

// call fn(...args) and show toast on success or error, rethrow
export function callWithToast(fn, successMessage) {
  return async (...args) => {
    try {
      const res = await fn(...args);
      showSuccess(successMessage || 'Done');
      return res;
    } catch (err) {
      const detail = err?.response?.data?.message || err?.response?.data || err?.message || 'Failed';
      showError(detail);
      throw err;
    }
  };
}


// Correct delete sequence utilities used by pages:
export async function unlinkThenDelete({ unlinkFn, deleteFn, unlinkArgs = [], deleteArgs = [] }) {
  // unlink first, ignore error on unlink? we prefer to propagate
  await unlinkFn(...unlinkArgs);
  await deleteFn(...deleteArgs);
}
