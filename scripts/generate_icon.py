from pathlib import Path
from PIL import Image, ImageDraw, ImageFilter

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "assets"
ASSETS.mkdir(exist_ok=True)
S = 1024
BG = (9, 10, 14, 255)
RED = (240, 68, 82, 255)
WHITE = (248, 245, 243, 255)


def star_points(cx: int, cy: int, outer: int, inner: int):
    points = []
    for i in range(8):
        import math
        radius = outer if i % 2 == 0 else inner
        angle = -math.pi / 2 + i * math.pi / 4
        points.append((cx + int(radius * math.cos(angle)), cy + int(radius * math.sin(angle))))
    return points


def mark_layer(size: int, transparent: bool = True) -> Image.Image:
    scale = size / S
    layer = Image.new("RGBA", (size, size), (0, 0, 0, 0) if transparent else BG)
    d = ImageDraw.Draw(layer)
    cx = cy = size // 2
    # Three clean orbit rings create the assistant/voice motif.
    for diameter, alpha, width in [(570, 100, 7), (430, 165, 9), (286, 240, 11)]:
        r = int(diameter * scale / 2)
        color = (240, 68, 82, alpha)
        d.ellipse((cx-r, cy-r, cx+r, cy+r), outline=color, width=max(1, int(width*scale)))
    # Four-point spark: a simple, legible original symbol, not a copied wordmark.
    outer = int(134 * scale)
    inner = int(34 * scale)
    d.polygon(star_points(cx, cy, outer, inner), fill=WHITE)
    # Small crimson core and lower signal stroke.
    r = int(18 * scale)
    d.ellipse((cx-r, cy-r, cx+r, cy+r), fill=RED)
    sw = max(2, int(12 * scale))
    d.arc((cx-int(95*scale), cy+int(132*scale), cx+int(95*scale), cy+int(234*scale)), start=18, end=162, fill=RED, width=sw)
    return layer


def launcher_icon() -> Image.Image:
    base = Image.new("RGBA", (S, S), BG)
    glow = Image.new("RGBA", (S, S), (0, 0, 0, 0))
    gd = ImageDraw.Draw(glow)
    gd.ellipse((190, 190, 834, 834), fill=(190, 35, 50, 105))
    glow = glow.filter(ImageFilter.GaussianBlur(105))
    base.alpha_composite(glow)
    base.alpha_composite(mark_layer(S, transparent=True))
    return base


launcher_icon().save(ASSETS / "icon.png")
mark_layer(1024, transparent=True).save(ASSETS / "android-icon-foreground.png")
Image.new("RGBA", (1024, 1024), BG).save(ASSETS / "android-icon-background.png")
mono = Image.new("RGBA", (1024, 1024), (0, 0, 0, 0))
md = ImageDraw.Draw(mono)
md.polygon(star_points(512, 512, 134, 34), fill=(255, 255, 255, 255))
mono.save(ASSETS / "android-icon-monochrome.png")
mark_layer(512, transparent=True).save(ASSETS / "splash-icon.png")
launcher_icon().resize((256, 256), Image.Resampling.LANCZOS).save(ASSETS / "favicon.png")
print("Generated Saathi icon assets in", ASSETS)
