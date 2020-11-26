import { loadTable } from "./load-table/loadTable.js";
import { filter } from "./search/search.js";

document.addEventListener("DOMContentLoaded", function(event) {
    loadTable();

    const doFilter = document.querySelector('#filter');
    doFilter.addEventListener('click', (event) => {
        const filterTerm = document.querySelector('#filterTerm');
        filter(filterTerm.value || '');
    });
});