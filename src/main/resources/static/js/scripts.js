document.getElementById('createUserForm').addEventListener('submit', function(e) {
    e.preventDefault();
    createUser();
});

async function createUser() {
    const user = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        emailAddress: document.getElementById('emailAddress').value
    };

    const response = await fetch('/api/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (response.ok) {
        alert('User created successfully!');
        document.getElementById('createUserForm').reset();
        getUsers();
    } else {
        alert('Failed to create user');
    }
}

async function getUsers() {
    const response = await fetch('/api/users');
    const users = await response.json();
    const userTable = document.getElementById('userTable').getElementsByTagName('tbody')[0];
    userTable.innerHTML = '';

    users.forEach(user => {
        const row = userTable.insertRow();
        row.insertCell(0).innerText = user.id;
        row.insertCell(1).innerText = user.firstName;
        row.insertCell(2).innerText = user.lastName;
        row.insertCell(3).innerText = user.dateOfBirth;
        row.insertCell(4).innerText = user.phoneNumber;
        row.insertCell(5).innerText = user.emailAddress;
        const actionsCell = row.insertCell(6);
        actionsCell.innerHTML = `
            <button class="btn btn-warning btn-sm" onclick="navigateToEdit(${user.id})">Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.id})">Delete</button>`;
    });
}

async function searchUsers() {
    const searchTerm = document.getElementById('searchTerm').value;
    const response = await fetch(`/api/users?search=${searchTerm}`);
    const users = await response.json();
    const userTable = document.getElementById('userTable').getElementsByTagName('tbody')[0];
    userTable.innerHTML = '';

    users.forEach(user => {
        const row = userTable.insertRow();
        row.insertCell(0).innerText = user.id;
        row.insertCell(1).innerText = user.firstName;
        row.insertCell(2).innerText = user.lastName;
        row.insertCell(3).innerText = user.dateOfBirth;
        row.insertCell(4).innerText = user.phoneNumber;
        row.insertCell(5).innerText = user.emailAddress;
        const actionsCell = row.insertCell(6);
        actionsCell.innerHTML = `
            <button class="btn btn-warning btn-sm" onclick="navigateToEdit(${user.id})">Edit</button>
            <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.id})">Delete</button>`;
    });
}

async function deleteUser(id) {
    const response = await fetch(`/api/users/${id}`, { method: 'DELETE' });

    if (response.ok) {
        alert('User deleted successfully');
        getUsers();
    } else {
        alert('Failed to delete user');
    }
}

function navigateToEdit(id) {
    window.location.href = `/edit-user.html?id=${id}`;
}

document.addEventListener('DOMContentLoaded', getUsers);
