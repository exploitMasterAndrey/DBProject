async function delete_auto(){
    const input = document.getElementById('deleteAutoInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteAuto', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_auto(){
    const input1 = document.getElementById('clientIDInput')
    const input2 = document.getElementById('autoBrandInput')
    const input3 = document.getElementById('autoYearInput')
    const input4 = document.getElementById('autoTypeInput')

    const clientID = input1.value
    const brand = input2.value
    const year = input3.value
    const type = input4.value

    const res = await fetch('http://localhost:8080/createAuto', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'clientID': clientID
        },
        body: JSON.stringify({brand, year, type})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
}

async function update_auto(){
    const input1 = document.getElementById('autoIDInput')
    const input2 = document.getElementById('newClientIDInput')
    const input3 = document.getElementById('newAutoBrandInput')
    const input4 = document.getElementById('newAutoYearInput')
    const input5 = document.getElementById('newAutoTypeInput')

    const id = input1.value
    const clientID = input2.value
    const brand = input3.value
    const year = input4.value
    const type = input5.value

    const res = await fetch('http://localhost:8080/updateAuto', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, brand, year, type, clientID})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
}

