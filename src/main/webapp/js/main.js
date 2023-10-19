function showErrorsFor2Seconds() {
    var errorContainer = document.getElementById("error-container");
    if (errorContainer) {
        errorContainer.style.display = "block";
        setTimeout(function() {
            errorContainer.style.display = "none";
        }, 4000);
    }
}

// Call the function when the page loads
window.addEventListener("load", showErrorsFor2Seconds);