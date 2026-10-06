from pathlib import Path
from PIL import Image, ImageDraw, ImageChops

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "assets"
RES = ROOT / "android" / "app" / "src" / "main" / "res"
DENSITIES = {"mdpi": 48, "hdpi": 72, "xhdpi": 96, "xxhdpi": 144, "xxxhdpi": 192}


def resized(path: Path, size: int) -> Image.Image:
    return Image.open(path).convert("RGBA").resize((size, size), Image.Resampling.LANCZOS)


def circle_mask(size: int) -> Image.Image:
    mask = Image.new("L", (size, size), 0)
    inset = round(size * 0.055)
    ImageDraw.Draw(mask).ellipse((inset, inset, size - inset - 1, size - inset - 1), fill=255)
    return mask


def save_webp(image: Image.Image, path: Path) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    image.save(path, format="WEBP", lossless=True, quality=100, method=6)


for density, size in DENSITIES.items():
    folder = RES / f"mipmap-{density}"
    launcher = resized(ASSETS / "icon.png", size)
    launcher.putalpha(ImageChops.multiply(launcher.getchannel("A"), circle_mask(size)))
    save_webp(launcher, folder / "ic_launcher.webp")
    save_webp(resized(ASSETS / "android-icon-background.png", size), folder / "ic_launcher_background.webp")
    save_webp(resized(ASSETS / "android-icon-foreground.png", size), folder / "ic_launcher_foreground.webp")
    save_webp(resized(ASSETS / "android-icon-monochrome.png", size), folder / "ic_launcher_monochrome.webp")

print(f"Prepared four native WebP launcher resources per density in {RES}")
