import { Container } from '@/components/container/Container'
import { Header } from '@/components/header/header'
import { Toaster } from '@/components/ui/toaster'
import { Settings } from '@/constants/settings'
import { ThemeProvider } from '@/providers/themeProvider'
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import './globals.css'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
	title: Settings.appName,
	description: Settings.appDescription,
}

// {/* <body style={{"border-color: 'red';"}}> */}

export default function RootLayout({
	children,
}: Readonly<{
	children: React.ReactNode
}>) {
	return (
    <html lang="en" suppressHydrationWarning={true}>
      <body>
        <ThemeProvider
          attribute="class"
          defaultTheme="system"
          enableSystem
          disableTransitionOnChange
        >
          <Header />
          <Container>{children}</Container>
          <Toaster />
        </ThemeProvider>
      </body>
    </html>
  );
}
