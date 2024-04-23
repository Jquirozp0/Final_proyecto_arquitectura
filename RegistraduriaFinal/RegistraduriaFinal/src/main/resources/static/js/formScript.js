function showForm(formId) {
    // Ocultar todos los formularios
    var forms = document.getElementsByTagName('form');
    for (var i = 0; i < forms.length; i++) {
        forms[i].style.display = 'none';
    }
    // Mostrar el formulario seleccionado
    document.getElementById(formId).style.display = 'block';
}


