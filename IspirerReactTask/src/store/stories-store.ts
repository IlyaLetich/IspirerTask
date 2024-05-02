import { makeAutoObservable } from "mobx";
import { storiesApi } from "../api/stories-api";

export type StoryInfo = {
    by?: string,
    descendants?: number,
    id?: number,
    kids?: number[],
    score?: number,
    time?: number,
    title?: string
    type?: string
    url?: string
}

export class StoriesStore {
    stories: number[] = [];
    storiesList: StoryInfo[] = [];
    page: number = 0;
    isLoaded: boolean = false;

    constructor() {
        makeAutoObservable(this);
    }

    setStories(value: number[]) {
        this.stories = value;
    }
    setStoriesList(value: StoryInfo[]) {
        this.storiesList = value;
    }
    setIsloaded(value: boolean) {
        this.isLoaded = value;
    }
    async fetchStories(): Promise<void> {
        this.setStories([]);
        const result = await storiesApi.getStories();
        this.setStories(result);
    }

    public async fetchStoriesList(): Promise<void> {
        const paginatedStoriesList = this.stories.slice(0, 5);

        for (let i = 0; i < paginatedStoriesList.length; i++) {
            const item = paginatedStoriesList[i];
            let itemData = await storiesApi.getStoriesData(item);
            this.storiesList.push(itemData);
        }
    }
}

export const storiesStore = new StoriesStore();
