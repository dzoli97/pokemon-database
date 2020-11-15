const apiURL = 'https://pokeapi.co/api/v2/pokemon';

const openPokemon = (event, path, id) => {
    window.open(`${window.location.host}${path}/${id}`, `pokemon-database-detailed_${id}`);
}

const loadPokemons = async () => {
    let data = await fetch(apiURL);
    data = await data.json();

    const fetchPokemons = data.results.map((pokemon) => {
        return new Promise((resolve, reject) => {
            fetch(pokemon.url)
                .then((response) => {
                    return response.json();
                })
                .then((pokemonJson) => resolve(pokemonJson))
                .catch((err) => reject(err));
        });
    });

    const pokemons = await Promise.all(fetchPokemons);

    return pokemons.map((detailedPokemon) => ({
        name: detailedPokemon.name,
        imageUrl: detailedPokemon.sprites.back_default,
    }));
}

const createTd = (...content) => {
    const td = document.createElement('td');
    td.append(...content);
    return td;
}

const createImage = (url) => {
    const image = document.createElement('img');
    image.src = url;
    return image;
};

function isCreator(user) {
    return true; //TODO: Pass user, evaluate if pokemon is created by he/she.
}

function createButton(label, onClick) {
    const cssClasses = 'btn btn-secondary btn-lg btn-block';
    const button = document.createElement('button');
    button.type = 'button';
    button.innerHTML = label;
    button.className = cssClasses;
    button.onclick = onClick;
    return button;
}

function createControlButtons(id, name) {
    const deleteButton = createButton('Delete', (event) => {
        //TODO: Send delete request to server

        if(window.confirm(`Are you sure you want to delete ${name}?`)) {
            for( let pathElement of event.path) {
                if(pathElement.tagName === 'TR') {
                    pathElement.remove();
                    break;
                }
            }
        }
    });

    const editButton = createButton('Edit',
        (event) => openPokemon(event, '/pokemon/edit', id));

    return [editButton, deleteButton];
}

export const loadTable = async (user) => {
    const selector = '#pokemon-data > tbody';

    const pokemons = await loadPokemons();

    const tbody = document.querySelector(selector);
    for( let pokemon of pokemons ) {
        const row = document.createElement('tr');
        const rowContent = [];
        rowContent.push(createTd((createImage(pokemon.imageUrl))));
        rowContent.push(createTd(pokemon.name));
        rowContent.push(createTd('creator')); //TODO: Creator
        
        const buttons = [];
        const pokemonId = Math.floor(Math.random() * 10); //TODO: pokemon.id
        buttons.push(createButton('Open',
            (event) => openPokemon(event, '/pokemon/details', pokemonId)));
        if(isCreator(user)) {
            //const [button1, button2] = createControlButtons(pokemon.id);
            buttons.push(...createControlButtons(pokemonId, pokemon.name));
        }
        rowContent.push(createTd(...buttons))
        
        rowContent.forEach(td => {
            row.appendChild(td);
        });

        tbody.appendChild(row);
    }

}