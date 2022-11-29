async function delete_branch(){
    const input = document.getElementById('deleteBranchInput')
    const id = input.value
    const res = await fetch('http://localhost:8080/deleteBranch', {
        method: 'DELETE',
        headers:{
            'Content-Type': 'application/json',
            'id': id
        }
    })
    input.value = ''
}

async function add_branch(){
    const input1 = document.getElementById('specializationInput')
    const input2 = document.getElementById('addressInput')

    const specialization = input1.value
    const address = input2.value

    const res = await fetch('http://localhost:8080/createBranch', {
        method: 'POST',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({specialization, address})
    })

    input1.value = ''
    input2.value = ''
}

async function update_branch(){
    const input1 = document.getElementById('branchIDInput')
    const input2 = document.getElementById('newSpecializationInput')
    const input3 = document.getElementById('newAddressInput')

    const id = input1.value
    const specialization = input2.value
    const address = input3.value

    const res = await fetch('http://localhost:8080/updateBranch', {
        method: 'PUT',
        headers:{
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id, specialization, address})
    })

    input1.value = ''
    input2.value = ''
    input3.value = ''
}

