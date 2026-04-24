document.addEventListener('DOMContentLoaded', function() {
    const addBlogBtn = document.getElementById('add-blog-btn');
    const blogForm = document.getElementById('blog-form');
    const blogModal = document.getElementById('blog-modal');
    const closeModalBtn = document.querySelector('.close-modal');

    if (addBlogBtn && blogModal) {
        addBlogBtn.addEventListener('click', () => {
            blogModal.style.display = 'flex';
        });
    }

    if (closeModalBtn && blogModal) {
        closeModalBtn.addEventListener('click', () => {
            blogModal.style.display = 'none';
        });
    }

    if (blogForm) {
        blogForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const data = {
                id: document.getElementById('user-id').value,
                blogName: document.getElementById('blog-name').value
            };

            fetch('/api/v1/blogs', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Failed to create Blog');
                }
            }).catch(error => {
                console.error('Error:', error);
                alert('An error occurred while creating the Blog');
            });
        });
    }
});