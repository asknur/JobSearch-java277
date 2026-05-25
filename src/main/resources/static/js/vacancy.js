function applyFilter() {
    const sort = document.getElementById('filterSort').value;

    localStorage.setItem('vacancyFilter', JSON.stringify({sort: sort}));

    const items = Array.from(document.querySelectorAll('.vacancy-item'));

    items.sort(function (a, b) {
        const dateA = new Date(a.dataset.date);
        const dateB = new Date(b.dataset.date);
        return sort === 'oldest' ? dateA - dateB : dateB - dateA;
    });

    const list = document.getElementById('vacancy-list');
    items.forEach(function (item) {
        list.appendChild(item);
    });
}

window.addEventListener('load', function () {
    const saved = localStorage.getItem('vacancyFilter');
    if (saved) {
        const filter = JSON.parse(saved);
        document.getElementById('filterSort').value = filter.sort || 'newest';
        applyFilter();
    }
});