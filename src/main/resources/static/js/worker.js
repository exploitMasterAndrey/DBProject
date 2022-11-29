async function delete_worker(){
    const input = document.getElementById('deleteWorkerInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteWorker', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_worker(){
    const input1 = document.getElementById('surnameInput')
    const input2 = document.getElementById('nameInput')
    const input3 = document.getElementById('patronymicInput')
    const input4 = document.getElementById('birthDateInput')
    const input5 = document.getElementById('phoneInput')
    const input6 = document.getElementById('experienceInput')
    const input7 = document.getElementById('positionInput')
    const input8 = document.getElementById('branchIDInput')


    const surname = input1.value
    const name = input2.value
    const patronymic = input3.value
    const birthDate = input4.value
    const phone = input5.value
    const experience = input6.value
    const position = input7.value
    const branchID = input8.value

    const res = await fetch('http://localhost:8080/createWorker', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'branchID': branchID
        },
        body: JSON.stringify({surname, name, patronymic, birthDate, phone, experience, position})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
    input6.value = ''
    input7.value = ''
    input8.value = ''
}

async function update_worker(){
    const input1 = document.getElementById('workerIDInput')
    const input2 = document.getElementById('newSurnameInput')
    const input3 = document.getElementById('newNameInput')
    const input4 = document.getElementById('newPatronymicInput')
    const input5 = document.getElementById('newBirthDateInput')
    const input6 = document.getElementById('newPhoneInput')
    const input7 = document.getElementById('newExperienceInput')
    const input8 = document.getElementById('newPositionInput')
    const input9 = document.getElementById('newBranchIDInput')


    const id = input1.value
    const surname = input2.value
    const name = input3.value
    const patronymic = input4.value
    const birthDate = input5.value
    const phone = input6.value
    const experience = input7.value
    const position = input8.value
    const branchID = input9.value

    const res = await fetch('http://localhost:8080/updateWorker', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, surname, name, patronymic, birthDate, phone, experience, position, branchID})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
    input6.value = ''
    input7.value = ''
    input8.value = ''
    input9.value = ''
}

