async function delete_request(){
    const input = document.getElementById('deleteRequestInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteRequest', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_request(){
    const input1 = document.getElementById('dateInput')
    const input2 = document.getElementById('priceInput')
    const input3 = document.getElementById('commentInput')
    const input4 = document.getElementById('clientIDInput')
    const input5 = document.getElementById('workerIDInput')

    const date = input1.value
    const price = input2.value
    const comment = input3.value
    const clientID = input4.value
    const workersID = input5.value

    const res = await fetch('http://localhost:8080/createRequest', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'clientID': clientID
        },
        body: JSON.stringify({date, price, comment, workersID})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
}

async function update_request(){
    const input1 = document.getElementById('requestIDInput')
    const input2 = document.getElementById('newDateInput')
    const input3 = document.getElementById('newPriceInput')
    const input4 = document.getElementById('newCommentInput')
    const input5 = document.getElementById('newClientIDInput')
    const input6 = document.getElementById('newWorkerIDInput')

    const id = input1.value
    const date = input2.value
    const price = input3.value
    const comment = input4.value
    const clientID = input5.value
    const workersID = input6.value

    const res = await fetch('http://localhost:8080/updateRequest', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, date, price, comment, clientID, workersID})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
    input5.value = ''
    input6.value = ''
}

