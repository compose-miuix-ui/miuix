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
