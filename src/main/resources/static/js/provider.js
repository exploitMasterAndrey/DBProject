async function delete_provider(){
    const input = document.getElementById('deleteProviderInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteProvider', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_provider(){
    const input1 = document.getElementById('deliveryPartTypeInput')
    const input2 = document.getElementById('nameInput')
    const input3 = document.getElementById('branchIDInput')

    const deliveryPartType = input1.value
    const name = input2.value
    const branchID = input3.value

    const res = await fetch('http://localhost:8080/createProvider', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json',
            'branchID': branchID
        },
        body: JSON.stringify({deliveryPartType, name})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
}

async function update_provider(){
    const input1 = document.getElementById('providerIDInput')
    const input2 = document.getElementById('newDeliveryPartTypeInput')
    const input3 = document.getElementById('newNameInput')
    const input4 = document.getElementById('newBranchIDInput')

    const id = input1.value
    const deliveryPartType = input2.value
    const name = input3.value
    const branchID = input4.value

    const res = await fetch('http://localhost:8080/updateProvider', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, deliveryPartType, name, branchID})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
    input4.value = ''
}

