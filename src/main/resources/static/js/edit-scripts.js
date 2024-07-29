document.getElementById('editUserForm').addEventListener('submit', function(e) {
    e.preventDefault();
    updateUser();
});

async function getUserDetails(id) {
    const response = await fetch(`/api/users/${id}`);
    const user = await response.json();

    document.getElementById('editUserId').value = user.id;
    document.getElementById('editFirstName').value = user.firstName;
    document.getElementById('editLastName').value = user.lastName;
    document.getElementById('editDateOfBirth').value = user.dateOfBirth;
    document.getElementById('editPhoneNumber').value = user.phoneNumber;
    document.getElementById('editEmailAddress').value = user.emailAddress;
}

async function updateUser() {
    const id = document.getElementById('editUserId').value;
    const user = {
        firstName: document.getElementById('editFirstName').value,
        lastName: document.getElementById('editLastName').value,
        dateOfBirth: document.getElementById('editDateOfBirth').value,
        phoneNumber: document.getElementById('editPhoneNumber').value,
        emailAddress: document.getElementById('editEmailAddress').value
    };

    const response = await fetch(`/api/users/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (response.ok) {
        alert('User updated successfully!');
        window.location.href = '/';
    } else {
        alert('Failed to update user');
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('id');
    if (userId) {
        getUserDetails(userId);
    }
});
