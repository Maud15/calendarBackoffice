let $form = document.getElementById('formDetailsUser');

document.getElementById('editUser').addEventListener('click',e => {
    e.target.classList.add('hidden');
    $form.classList.remove('aria-disabled');
    $form.querySelectorAll('input:not(.forbidden)').forEach(
        e => e.removeAttribute('disabled')
    );
})

document.getElementById('cancel-edit').addEventListener('click',() => {
    document.getElementById('editUser').classList.remove('hidden');
    $form.classList.add('aria-disabled');
    $form.querySelectorAll('input').forEach(
        e => e.setAttribute('disabled','')
    );
})



