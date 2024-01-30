function goBack() {
    window.history.back();
}

function searchDossier() {
    var dossierId = document.getElementById("dossierIdInput").value;
    if (dossierId.trim() === "") {
        alert("Please provide an ID");
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/dossiers/search?id=" + dossierId);
    xhr.onload = function () {
        if (xhr.status === 200) {
            // If dossier is found, update the page content
            document.open();
            document.write(xhr.responseText);
            document.close();
        } else {
            // If dossier is not found, display an alert
            alert("Dossier not found");
        }
    };
    xhr.send();
}




// articles js

/*<![CDATA[*/
document.addEventListener('DOMContentLoaded', function() {
    fetch('/articles/articles-json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data && data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var articleOption = '<option value="' + data[i].reference + '">' + data[i].nom + '</option>';
                    document.getElementById('article').insertAdjacentHTML('beforeend', articleOption);
                }
            } else {
                console.error("No articles found or 'data' is null.");
            }
        })
        .catch(error => {
            console.error('Error during fetch operation:', error);
        });
});
/*]]>*/

//dossier js

/*<![CDATA[*/

document.addEventListener('DOMContentLoaded', function () {

    fetch('/techniciens/techniciens-json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(techniciensData => {

            const editDossierModals = document.querySelectorAll('.editDossierModal');

            editDossierModals.forEach(modal => {
                const technicienSelect = modal.querySelector('#technicien');

                technicienSelect.innerHTML = '';

                techniciensData.forEach(technicien => {
                    const technicienOption = `<option value="${technicien.idTechnicien}">${technicien.nom} ${technicien.prenom} - ${technicien.specialite}</option>`;
                    technicienSelect.insertAdjacentHTML('beforeend', technicienOption);
                });
            });
        })
        .catch(error => {
            console.error('Error during fetch operation:', error);
        });
});

/*]]>*/

// time js

function updateTime() {
    var now = new Date();
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' };
    var formattedDate = now.toLocaleDateString('fr-FR', options);

    document.getElementById('currentDateTime').textContent = formattedDate;
}

// Update the time every second
setInterval(updateTime, 1000);

// Initial call to set the time immediately
updateTime();



