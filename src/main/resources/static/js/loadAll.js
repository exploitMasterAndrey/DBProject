window.addEventListener('DOMContentLoaded', fetchAll)

async function fetchAll(){
    const autosResponse = await fetch('http://localhost:8080/getAllAutos')
    const autos = await autosResponse.json()
    autos.forEach(auto => autosToHTML(auto))

    const branchesResponse = await fetch('http://localhost:8080/getAllBranches')
    const branches = await branchesResponse.json()
    branches.forEach(branch => branchesToHTML(branch))

    const clientsResponse = await fetch('http://localhost:8080/getAllClients')
    const clients = await clientsResponse.json()
    clients.forEach(client => clientsToHTML(client))

    const providersResponse = await fetch('http://localhost:8080/getAllProviders')
    const providers = await providersResponse.json()
    providers.forEach(provider => providersToHTML(provider))

    const workersResponse = await fetch('http://localhost:8080/getAllWorkers')
    const workers = await workersResponse.json()
    workers.forEach(worker => workersToHTML(worker))

    const requestsResponse = await fetch('http://localhost:8080/getAllRequests')
    const requests = await requestsResponse.json()
    requests.forEach(request => requestsToHTML(request))
}

function autosToHTML({id, client, brand, year, type}){
    const autosList = document.getElementById('cars')

    autosList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${brand}</td>
                <td>${year}</td>
                <td>${type}</td>
                <td>${client.surname} ${client.name} ${client.patronymic}</td>
            </tr>
    `)
}

function clientsToHTML({id, surname, name, patronymic, birthDate, phone}){
    const clientsList = document.getElementById('clients')

    clientsList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${surname}</td>
                <td>${name}</td>
                <td>${patronymic}</td>
                <td>${birthDate}</td>
                <td>${phone}</td>                
            </tr>
    `)
}

function requestsToHTML({id, date, client, price, comment}){
    const requestsList = document.getElementById('requests')

    requestsList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${date}</td>
                <td>${client.surname} ${client.name} ${client.patronymic}</td>
                <td>${price}</td>
                <td>${comment}</td>                                
            </tr>
    `)
}

function workersToHTML({id, branch, request, surname, name, patronymic, birthDate, phone, experience, position}){
    const workersList = document.getElementById('workers')

    if (branch !== null && request !== null) {
        workersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${surname}</td>
                <td>${name}</td>
                <td>${patronymic}</td>
                <td>${birthDate}</td>
                <td>${phone}</td>   
                <td>${experience}</td>         
                <td>${position}</td> 
                <td>${branch.id}</td> 
                <td>${request.id}</td>  
            </tr>
    `)
    }
    else if(branch == null && request !== null){
        workersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${surname}</td>
                <td>${name}</td>
                <td>${patronymic}</td>
                <td>${birthDate}</td>
                <td>${phone}</td>   
                <td>${experience}</td>         
                <td>${position}</td> 
                <td>Филиал не установлен</td> 
                <td>${request.id}</td>  
            </tr>
    `)
    }
    else if(branch !== null && request == null){
        workersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${surname}</td>
                <td>${name}</td>
                <td>${patronymic}</td>
                <td>${birthDate}</td>
                <td>${phone}</td>   
                <td>${experience}</td>         
                <td>${position}</td> 
                <td>${branch.id}</td> 
                <td>Заявка не установлена</td>  
            </tr>
    `)
    }
    else {
        workersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${surname}</td>
                <td>${name}</td>
                <td>${patronymic}</td>
                <td>${birthDate}</td>
                <td>${phone}</td>   
                <td>${experience}</td>         
                <td>${position}</td> 
                <td>Филиал не установлен</td> 
                <td>Заявка не установлена</td>  
            </tr>
    `)
    }
}

function branchesToHTML({id, specialization, address}){
    const branchesList = document.getElementById('branches')

    branchesList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${specialization}</td>
                <td>${address}</td>                              
            </tr>
    `)
}

function providersToHTML({id, branch, deliveryPartType, name}){
    const providersList = document.getElementById('providers')

    if (branch == null) {
        providersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${deliveryPartType}</td>
                <td>${name}</td>    
                <td>Филиал не назначен</td>                          
            </tr>
    `)
    }
    else {
        providersList.insertAdjacentHTML('beforeend', `
    <tr>
                <th scope="row">${id}</th>
                <td>${deliveryPartType}</td>
                <td>${name}</td>    
                <td>${branch.id}</td>                          
            </tr>
    `)
    }
}