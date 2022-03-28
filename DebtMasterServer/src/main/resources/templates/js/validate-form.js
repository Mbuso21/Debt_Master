const submit = document.getElementById("submit");

submit.addEventListener("click", validate);

function validate(e) {
    e.preventDefault();

    const nameField = document.getElementById("name");
    let valid = true;

    if (!nameField.value) {
        const nameError = document.getElementById("nameError");
        nameError.classList.add("visible");
        nameField.classList.add("invalid");
        nameError.setAttribute("aria-hidden", false);
        nameError.setAttribute("aria-invalid", true);
    }
    return valid;
}