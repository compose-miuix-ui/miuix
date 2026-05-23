import { defineConfig } from "vitepress";
import locales from "./locales";
import {
  GitChangelog,
  GitChangelogMarkdownSection,
} from "@nolebase/vitepress-plugin-git-changelog/vite";

export default defineConfig({
  base: "/miuix/",
  title: "Miuix",
  locales: locales.locales,
  head: [
    // Auto-redirect first-time visitors
    ['script', {}, `(function(){
      try {
        if (typeof window === 'undefined' || typeof localStorage === 'undefined') return;
        var KEY = 'miuix-locale-auto-redirected';
        if (localStorage.getItem(KEY)) return;
        localStorage.setItem(KEY, '1');
        var lang = (navigator.language || '').toLowerCase();
        var isSimplifiedZh = lang === 'zh-cn' || lang === 'zh-sg' || lang.indexOf('zh-hans') === 0;
        if (!isSimplifiedZh) return;
        var base = '/miuix/';
        var path = window.location.pathname;
        if (path.indexOf(base) !== 0) return;
        if (path.indexOf(base + 'zh_CN') === 0) return;
        var rest = path.slice(base.length);
        window.location.replace(base + 'zh_CN/' + rest + window.location.search + window.location.hash);
      } catch (e) {}
    })();`],
    ['meta', { name: 'color-scheme', content: 'light dark' }],
    ['link', { rel: 'icon', href: '/miuix/Icon.webp' }],
    ['link', { rel: 'preconnect', href: 'https://cdn-font.hyperos.mi.com/font/css?family=MiSans_VF:VF:Chinese_Simplify,Latin&display=swap' }],
  ],
  markdown: {
    image: {
      lazyLoading: true,
    },
  },
  cleanUrls: true,
  themeConfig: {
    logo: "/Icon.webp",
    socialLinks: [
      { icon: 'github', link: 'https://github.com/compose-miuix-ui/miuix' }
    ],
    search: {
      provider: "local",
      options: {
        locales: {
          zh_CN: {
            translations: {
              button: {
                buttonText: "搜索",
                buttonAriaLabel: "搜索",
              },
              modal: {
                noResultsText: "无法找到相关结果",
                resetButtonTitle: "清除查询条件",
                footer: {
                  selectText: "选择",
                  navigateText: "切换",
                  closeText: "关闭",
                },
              },
            },
          },
        },
      },
    },
    docFooter: {
      prev: false,
      next: false,
    },
  },
  vite: {
    optimizeDeps: {
      exclude: [
        "@nolebase/vitepress-plugin-enhanced-readabilities/client",
        "vitepress",
        "@nolebase/ui",
      ],
    },
    ssr: {
      noExternal: [
        "@nolebase/vitepress-plugin-enhanced-readabilities",
        "@nolebase/ui",
      ],
    },
    plugins: [
      GitChangelog({
        repoURL: () => "https://github.com/compose-miuix-ui/miuix",
      }),
      GitChangelogMarkdownSection({
        sections: {
          disableContributors: true,
        },
      }),
    ],
  },
});
