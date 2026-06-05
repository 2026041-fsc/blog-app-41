
    // HTMLが全部読み込み終わるのを待ってから動かす
    document.addEventListener('DOMContentLoaded', () => {
        const hamburger = document.getElementById('hamburger');
        const navMenu = document.getElementById('nav-menu');

        // エラー防止のためのチェックを入れるとより安全！
        if (hamburger && navMenu) {
            hamburger.addEventListener('click', () => {
                navMenu.classList.toggle('open');
            });
        } else {
            console.error("ハンバーガーボタンかメニューが見つかりませんでした。IDが合っているか確認してください！");
        }
    });
