import fs from 'node:fs'
import path from 'node:path'
import { fileURLToPath } from 'node:url'
import type { Plugin } from 'vite'

const __filename = fileURLToPath(import.meta.url)
const __dirname = path.dirname(__filename)

function updateComponentJson() {
  const demoDir = path.resolve(__dirname, '../../demo/src/commonMain/kotlin')
  const publicDir = path.resolve(__dirname, '../../public')
  const componentJsonPath = path.join(publicDir, 'component.json')

  try {
    if (!fs.existsSync(demoDir)) {
      console.warn(`[update-component-json] Demo directory not found: ${demoDir}`)
      return
    }

    const files = fs.readdirSync(demoDir)
    const components = files
      .filter((file: string) => file.endsWith('Demo.kt') && file !== 'Demo.kt')
      .map((file: string) => file.replace('Demo.kt', ''))
      .sort()

    if (!fs.existsSync(publicDir)) {
      fs.mkdirSync(publicDir, { recursive: true })
    }

    fs.writeFileSync(componentJsonPath, JSON.stringify(components, null, 2))
    console.log(`[update-component-json] Updated component.json with ${components.length} components.`)
  } catch (error) {
    console.error('[update-component-json] Error updating component.json:', error)
  }
}

export const componentListPlugin = (): Plugin => {
  return {
    name: 'update-component-json',
    buildStart() {
      updateComponentJson()
    },
    configureServer(server) {
      updateComponentJson()
    }
  }
}
