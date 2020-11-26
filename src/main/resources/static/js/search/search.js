const removedElements = [];

function startsWith(element, filter) {
    const value = element.querySelector('td:nth-child(2)').innerHTML; //Second column
    return value.toLowerCase().startsWith(filter);
}

export function filter(filterTerm) {
    const lowerCaseFilterTerm = filterTerm.trim().toLowerCase();

    const selector = '#pokemon-data > tbody';
    const tbody = document.querySelector(selector);

    const elements = tbody.querySelectorAll('tr');

    const removableAttachedELements = Array.from(elements).filter((element) => !startsWith(element, lowerCaseFilterTerm));
    const appendableElements = removedElements.filter((element) => startsWith(element, lowerCaseFilterTerm));

    for(let element of removableAttachedELements) {
        removedElements.push(element);
        element.remove();
    }

    for(let element of appendableElements) {
        tbody.appendChild(element);
        const index = removedElements.indexOf(element);
        if(index > -1) {
            removedElements.splice(index, 1);
        }
    }
}