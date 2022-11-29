async function delete_client(){
    const input = document.getElementById('deleteClientInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteClient', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_client(){
    const input1 = document.getElementById('surnameInput')
    const input2 = document.getElementById('nameInput')
    const input3 = document.getElementById('patronymicInput')
    const input4 = document.getElementById('birthDateInput')
    const input5 = document.getElementById('phoneInput')

    const surname = input1.value
    const name = input2.value
    const patronymic = input3.value
    const birthDate = input4.value
    const phone = input5.value

    const res = await fetch('http://localhost:8080/createClient', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({surname, name, patronymic, birthDate, phone})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
}

async function update_client(){
    const input1 = document.getElementById('clientIDInput')
    const input2 = document.getElementById('newSurnameInput')
    const input3 = document.getElementById('newNameInput')
    const input4 = document.getElementById('newPatronymicInput')
    const input5 = document.getElementById('newBirthDateInput')
    const input6 = document.getElementById('newPhoneInput')

    const id = input1.value
    const surname = input2.value
    const name = input3.value
    const patronymic = input4.value
    const birthDate = input5.value
    const phone = input6.value

    const res = await fetch('http://localhost:8080/updateClient', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, surname, name, patronymic, birthDate, phone})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
    input6.value = ''
}

