import mediumZoom from "medium-zoom";

import giscusTalk from "vitepress-plugin-comment-with-giscus";

import {
  NolebaseEnhancedReadabilitiesMenu,
  NolebaseEnhancedReadabilitiesScreenMenu,
  InjectionKey as InjectionKeyEnhancedReadabilities,
} from "@nolebase/vitepress-plugin-enhanced-readabilities/client";
import "@nolebase/vitepress-plugin-enhanced-readabilities/client/style.css";

import {
  NolebaseGitChangelogPlugin,
  InjectionKey as InjectionKeyGitChangelogPlugin,
} from "@nolebase/vitepress-plugin-git-changelog/client";
import "@nolebase/vitepress-plugin-git-changelog/client/style.css";

import type { App } from "vue";
import { onMounted, watch, nextTick, h } from "vue";

import { useData, useRoute } from "vitepress";
import DefaultTheme from "vitepress/theme";

import "./style/index.css";

export default {
  extends: DefaultTheme,

  /* Nólëbase Integrations */
  Layout: () => {
    return h(DefaultTheme.Layout, null, {
      "nav-bar-content-after": () => h(NolebaseEnhancedReadabilitiesMenu),
      "nav-screen-content-after": () =>
        h(NolebaseEnhancedReadabilitiesScreenMenu),
    });
  },
  enhanceApp({ app }: { app: App }) {
    app.use(NolebaseGitChangelogPlugin);
    app.provide(InjectionKeyGitChangelogPlugin, {
      hideChangelogNoChangesText: true,
      displayAuthorsInsideCommitLine: true,
      locales: {
        zh_CN: {
          changelog: {
            title: "变更日志",
            noData: "暂无最近变更日志",
            viewFullHistory: "查看完整变更日志",
            committedOn: " 提交于 {{date}}",
            lastEdited: "最后编辑于 {{daysAgo}}",
            lastEditedDateFnsLocaleName: "zhCN",
          },
        },
      },
    });
    app.provide(InjectionKeyEnhancedReadabilities, {
      locales: {
        zh_CN: {
          title: {
            title: "界面设置",
            titleAriaLabel: "界面设置菜单",
          },
          layoutSwitch: {
            title: "布局切换",
            titleAriaLabel: "布局切换菜单",
            titleHelpMessage: "切换不同的页面布局以获得最佳阅读体验",
            titleScreenNavWarningMessage: "在小屏幕上部分布局可能无法正确显示",
            optionFullWidth: "全宽",
            optionFullWidthAriaLabel: "切换为全宽布局",
            optionFullWidthHelpMessage: "内容区域与侧边栏最大宽度",
            optionSidebarWidthAdjustableOnly: "仅调整侧边栏",
            optionSidebarWidthAdjustableOnlyAriaLabel: "切换为仅可调整侧边栏宽度的布局",
            optionSidebarWidthAdjustableOnlyHelpMessage: "内容宽度固定，仅调整侧边栏",
            optionBothWidthAdjustable: "可调内容与侧边栏",
            optionBothWidthAdjustableAriaLabel: "切换为可调整内容和侧边栏的布局",
            optionBothWidthAdjustableHelpMessage: "自定义自由度最大",
            optionOriginalWidth: "原始布局",
            optionOriginalWidthAriaLabel: "恢复为原始布局",
            optionOriginalWidthHelpMessage: "使用默认页面布局设置",
            contentLayoutMaxWidth: {
              title: "内容最大宽度",
              titleAriaLabel: "设置内容最大宽度",
              titleHelpMessage: "调整主要内容区的最大宽度",
              titleScreenNavWarningMessage: "在小屏幕设备上可能无效",
              slider: "内容宽度滑块",
              sliderAriaLabel: "滑动调整内容最大宽度",
              sliderHelpMessage: "单位为 px",
            },
            pageLayoutMaxWidth: {
              title: "页面最大宽度",
              titleAriaLabel: "设置页面最大宽度",
              titleHelpMessage: "控制整个页面容器的最大宽度",
              titleScreenNavWarningMessage: "在小屏幕设备上可能无效",
              slider: "页面宽度滑块",
              sliderAriaLabel: "滑动调整页面宽度",
              sliderHelpMessage: "单位为 px",
            },
          },
          spotlight: {
            title: "聚焦模式",
            titleAriaLabel: "聚焦模式切换",
            titleHelpMessage: "开启后将高亮当前阅读区域",
            titleScreenNavWarningMessage: "小屏幕设备上可能无法正常显示",
            optionOn: "开启",
            optionOnAriaLabel: "开启聚焦模式",
            optionOnHelpMessage: "增强段落可读性",
            optionOff: "关闭",
            optionOffAriaLabel: "关闭聚焦模式",
            optionOffHelpMessage: "恢复正常显示",
            styles: {
              title: "聚焦样式",
              titleAriaLabel: "选择聚焦样式",
              titleHelpMessage: "设置聚焦时的显示方式",
              titleScreenNavWarningMessage: "某些样式在移动设备上可能无效",
              optionUnder: "底线高亮",
              optionUnderAriaLabel: "底部高亮样式",
              optionUnderHelpMessage: "在当前段落下方显示一条线",
              optionAside: "边栏高亮",
              optionAsideAriaLabel: "边栏高亮样式",
              optionAsideHelpMessage: "在页面边缘显示聚焦指示器",
            },
          },
        },
      },
    });
  },

  setup() {
    const { frontmatter } = useData();
    const route = useRoute();

    /* giscus */
    giscusTalk(
      {
        repo: "compose-miuix-ui/miuix-giscus",
        repoId: "R_kgDOQo99Eg",
        category: "General",
        categoryId: "DIC_kwDOQo99Es4Cz0CR",
        inputPosition: "bottom",
        locales: {
          zh_CN: "zh-CN",
          en_US: "en",
        },
        homePageShowComment: false,
      },
      {
        frontmatter,
        route,
      }
    );

    /* Medium Zoom */
    const initZoom = () => {
      mediumZoom(".main img", { background: "var(--vp-c-bg)" });
    };
    onMounted(() => {
      initZoom();
    });
    watch(
      () => route.path,
      () => nextTick(() => initZoom())
    );
  },
};
